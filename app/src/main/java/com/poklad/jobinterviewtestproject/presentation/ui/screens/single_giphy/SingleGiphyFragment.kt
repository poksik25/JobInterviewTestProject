package com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.poklad.jobinterviewtestproject.databinding.FragmentSingleGiphyBinding
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseFragment
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class SingleGiphyFragment : BaseFragment<FragmentSingleGiphyBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: SingleGiphyViewModel by viewModels {
        viewModelFactory
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSingleGiphyBinding {
        TODO("Not yet implemented")
    }
}