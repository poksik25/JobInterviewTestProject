package com.poklad.jobinterviewtestproject.data.repository

import com.poklad.jobinterviewtestproject.data.api.GiphyApi
import com.poklad.jobinterviewtestproject.data.model.GifItem
import com.poklad.jobinterviewtestproject.domain.repository.GiphyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyRepositoryImp @Inject constructor(
    private val giphyApi: GiphyApi
) : GiphyRepository {
     override suspend fun getTrendingGifs(): List<GifItem> {
        return giphyApi.getGifsList().data
    }
/*    override suspend fun getTrendingGifs(): GiphyResponse {
        return giphyApi.getGifsList()
    }*/
}