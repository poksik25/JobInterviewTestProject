package com.poklad.jobinterviewtestproject.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poklad.jobinterviewtestproject.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val dispatchers: CoroutineDispatchersProvider
) : ViewModel() {
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    private val _loadingFlow = MutableStateFlow(false)
    val loadingFlow = _loadingFlow.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable?>()
    val errorFlow = _errorFlow.asSharedFlow()
    protected fun emitError(throwable: Throwable?) {
        viewModelScope.launch {
            _errorFlow.emit(throwable)
        }
    }

    private fun showLoader() {
        _loadingFlow.value = true
    }

    private fun hideLoader() {
        _loadingFlow.value = false
    }

    protected fun launchMain(
        withLoader: Boolean = false,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(dispatchers.getMain() + coroutineExceptionHandler) {
            if (withLoader) {
                showLoader()
            }
            block()
            if (withLoader) {
                hideLoader()
            }
        }
    }
}