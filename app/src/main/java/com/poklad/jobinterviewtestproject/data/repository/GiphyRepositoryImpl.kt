package com.poklad.jobinterviewtestproject.data.repository

import com.poklad.jobinterviewtestproject.data.api.GiphyApi
import com.poklad.jobinterviewtestproject.data.mapper.GifItemResponseToGifItemMapper
import com.poklad.jobinterviewtestproject.di.annotations.ApplicationScope
import com.poklad.jobinterviewtestproject.domain.model.GifItem
import com.poklad.jobinterviewtestproject.domain.repository.GiphyRepository
import javax.inject.Inject
import javax.inject.Singleton

typealias GifItemMapper = GifItemResponseToGifItemMapper

@ApplicationScope
class GiphyRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi,
    private val mapper: GifItemMapper,
) : GiphyRepository {
    override suspend fun getTrendingGifs(): List<GifItem> {
        return giphyApi.getGifsList().data.map(mapper::map)
    }
}