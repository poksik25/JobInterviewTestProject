package com.poklad.jobinterviewtestproject.presentation.ui.screens.single_giphy

import com.poklad.jobinterviewtestproject.extensions.log
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import com.poklad.jobinterviewtestproject.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SingleGiphyViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
) : BaseViewModel(coroutineDispatchersProvider) {

    private val _selectedGif = MutableStateFlow<GifItemPresentation?>(null)
    val selectedGif = _selectedGif.asStateFlow()
    fun setSelectedGif(gif: GifItemPresentation) {
        _selectedGif.value = gif
    }

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
}