package com.poklad.jobinterviewtestproject.domain.usecases

import com.poklad.jobinterviewtestproject.data.model.GifItem
import com.poklad.jobinterviewtestproject.domain.repository.GiphyRepository
import javax.inject.Inject

class GetTrendingGifUseCase @Inject constructor(
    private val repository: GiphyRepository
) : UseCaseSuspend<Unit, List<GifItem>> {
    override suspend fun execute(parameter: Unit): List<GifItem> {
        return repository.getTrendingGifs()
    }
}