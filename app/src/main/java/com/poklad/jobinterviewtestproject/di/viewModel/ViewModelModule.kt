package com.poklad.jobinterviewtestproject.di.viewModel

import androidx.lifecycle.ViewModel
import com.poklad.jobinterviewtestproject.di.annotations.ViewModelKey
import com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list.GiphyListViewModel
import com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy.SingleGiphyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GiphyListViewModel::class)
    abstract fun bindGiphyListViewModel(yourViewModel: GiphyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SingleGiphyViewModel::class)
    abstract fun bindSingleGiphyViewModel(yourViewModel: SingleGiphyViewModel): ViewModel
}

