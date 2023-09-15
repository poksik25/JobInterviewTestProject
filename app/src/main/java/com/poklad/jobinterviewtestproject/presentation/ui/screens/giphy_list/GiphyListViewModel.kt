package com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list

import com.poklad.jobinterviewtestproject.domain.usecases.GetTrendingGifUseCase
import com.poklad.jobinterviewtestproject.extensions.coRunCatching
import com.poklad.jobinterviewtestproject.extensions.log
import com.poklad.jobinterviewtestproject.presentation.mapper.GifItemToGifPresentationMapper
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseViewModel
import com.poklad.jobinterviewtestproject.utils.CoroutineDispatchersProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GiphyListViewModel @Inject constructor(
    coroutineDispatchersProvider: CoroutineDispatchersProvider,
    private val getTrendingGifUseCase: GetTrendingGifUseCase,
    private val mapper: GifItemToGifPresentationMapper
) : BaseViewModel(coroutineDispatchersProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            log(throwable.message.toString())
        }
    private val _gifsList = MutableStateFlow<List<GifItemPresentation>>(emptyList())
    val gifsList = _gifsList.asStateFlow()

    init {
        loadGifs()
    }

    private fun loadGifs() {
        launchMain(withLoader = true) {
            fetchGifs(this)
        }
    }

    private suspend fun fetchGifs(scope: CoroutineScope) {
        scope.coRunCatching {
            withContext(dispatchers.getIO()) {
                getTrendingGifUseCase.execute(Unit).map(mapper::map)
            }
        }.onSuccess { productList ->
            _gifsList.value = productList
        }.onFailure {
            //handleError
        }
    }
}