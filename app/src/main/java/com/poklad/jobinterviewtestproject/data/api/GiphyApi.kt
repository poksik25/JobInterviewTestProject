package com.poklad.jobinterviewtestproject.data.api

import com.poklad.jobinterviewtestproject.data.model.GiphyResponse
import com.poklad.jobinterviewtestproject.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET(ApiConstants.GET_TRENDING_GIF)
    suspend fun getGifsList(
        @Query(ApiConstants.API_KEY_QUERY) apiKey: String = ApiConstants.API_KEY,
        @Query(ApiConstants.LIMIT) limit: Int = 30,
        @Query(ApiConstants.OFFSET) offset: Int = 0,
        @Query(ApiConstants.RATING) rating: String = "g"
    ): GiphyResponse
}