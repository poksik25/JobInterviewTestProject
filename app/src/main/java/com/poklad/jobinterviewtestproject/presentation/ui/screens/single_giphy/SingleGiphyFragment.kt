package com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.poklad.jobinterviewtestproject.GiphyApp
import com.poklad.jobinterviewtestproject.databinding.FragmentSingleGiphyBinding
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseFragment
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
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
        val gif =
            requireArguments().getParcelable<GifItemPresentation>(ARG_GIPHY)
                ?: throw IllegalArgumentException()//todo как улчшить єтот код
        binding.apply {
            Glide.with(this@SingleGiphyFragment)
                .asGif()
                .centerCrop()
                .load(gif.imageUrl)
                .into(ivGif)
        }
    }

    companion object {
        const val ARG_GIPHY = "ARG_GIPHY"
    }
}