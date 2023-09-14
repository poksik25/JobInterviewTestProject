package com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.jobinterviewtestproject.GiphyApp
import com.poklad.jobinterviewtestproject.data.model.GifItem
import com.poklad.jobinterviewtestproject.databinding.FragmentGiphyListBinding
import com.poklad.jobinterviewtestproject.extensions.invisible
import com.poklad.jobinterviewtestproject.extensions.toast
import com.poklad.jobinterviewtestproject.extensions.visible
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseFragment
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GiphyListFragment : BaseFragment<FragmentGiphyListBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: GiphyListViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentGiphyListBinding =
        FragmentGiphyListBinding.inflate(inflater)

    private val giphyAdapter: GiphyAdapter by lazy {
        GiphyAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GiphyApp.daggerAppComponent.inject(this@GiphyListFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingFlow.collect { showLoader ->
                    if (showLoader) {
                        binding.apply {
                            pbList.visible()
                            rvGiphyList.invisible()
                        }
                    } else {
                        binding.apply {
                            pbList.invisible()
                            rvGiphyList.visible()
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gifsList.collect { giphyList ->
                renderList(giphyList)
            }
        }
    }

    private fun renderList(giphyList: List<GifItem>) {
        giphyAdapter.list = giphyList
    }

    private fun initRecyclerView() {
        setUpRecyclerView(
            adapter = giphyAdapter,
            recyclerView = binding.rvGiphyList,
            orientation = LinearLayoutManager.VERTICAL,
            columns = 2
        ) { giphy ->
            requireContext().toast(giphy.id)
        }
    }
}