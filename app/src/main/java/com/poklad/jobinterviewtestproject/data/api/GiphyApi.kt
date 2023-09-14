package com.poklad.jobinterviewtestproject.data.api

import com.poklad.jobinterviewtestproject.data.model.GiphyResponse
import com.poklad.jobinterviewtestproject.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET(ApiConstants.GET_TRENDING_GIF)
    suspend fun getGifsList(
        @Query("api_key") apiKey: String = ApiConstants.API_KEY,
        @Query("limit")  limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g"
    ): GiphyResponse
}