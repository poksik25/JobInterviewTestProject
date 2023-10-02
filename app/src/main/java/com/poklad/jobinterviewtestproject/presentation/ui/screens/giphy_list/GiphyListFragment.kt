package com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.poklad.jobinterviewtestproject.GiphyApp
import com.poklad.jobinterviewtestproject.R
import com.poklad.jobinterviewtestproject.databinding.FragmentGiphyListBinding
import com.poklad.jobinterviewtestproject.extensions.invisible
import com.poklad.jobinterviewtestproject.extensions.toast
import com.poklad.jobinterviewtestproject.extensions.visible
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseFragment
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy.SingleGiphyFragment
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
        handleError()
    }

    private fun handleError() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.errorFlow.collect {
                    requireContext().toast(it?.message.toString())
                }
            }
        }
    }

    private fun renderList(giphyList: List<GifItemPresentation>) {
        giphyAdapter.list = giphyList
    }

    private fun initRecyclerView() {
        binding.rvGiphyList.apply {
            adapter = giphyAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        giphyAdapter.setOnclickListener { giphy ->
            navigateToFragment(
                R.id.action_giphyListFragment_to_singleGiphyFragment,
                bundleOf(SingleGiphyFragment.ARG_GIPHY to giphy)
            )
        }
    }
}