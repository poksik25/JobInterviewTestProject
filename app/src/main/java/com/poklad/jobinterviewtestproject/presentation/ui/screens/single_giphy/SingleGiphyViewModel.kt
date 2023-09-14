package com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy

import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import com.poklad.jobinterviewtestproject.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class SingleGiphyViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider
) : BaseViewModel(coroutineDispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = TODO("Not yet implemented")
}