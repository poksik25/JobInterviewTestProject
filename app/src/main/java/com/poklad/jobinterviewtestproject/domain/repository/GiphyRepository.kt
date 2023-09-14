package com.poklad.jobinterviewtestproject.domain.repository

import com.poklad.jobinterviewtestproject.data.model.GifItem

interface GiphyRepository {
    suspend fun getTrendingGifs(): List<GifItem>
}