package com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.poklad.jobinterviewtestproject.GiphyApp
import com.poklad.jobinterviewtestproject.R
import com.poklad.jobinterviewtestproject.databinding.FragmentSingleGiphyBinding
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseFragment
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SingleGiphyFragment : BaseFragment<FragmentSingleGiphyBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: SingleGiphyViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        GiphyApp.daggerAppComponent.inject(this@SingleGiphyFragment)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSingleGiphyBinding =
        FragmentSingleGiphyBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Gif..."
        }

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        val gif =
            requireArguments().getParcelable<GifItemPresentation>(ARG_GIPHY)
                ?: throw IllegalArgumentException()
        viewModel.setSelectedGif(gif)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedGif.collect { selectedGif ->
                    selectedGif?.let {
                        binding.apply {
                            Glide.with(this@SingleGiphyFragment)
                                .asGif()
                                .load(gif.imageUrl)
                                .into(ivGif)
                        }
                    } ?: R.drawable.pic_placeholder
                }
            }
        }
    }

    companion object {
        const val ARG_GIPHY = "ARG_GIPHY"
    }
}