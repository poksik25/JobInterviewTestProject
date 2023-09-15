package com.poklad.jobinterviewtestproject.domain.repository

import androidx.paging.PagingData
import com.poklad.jobinterviewtestproject.domain.model.GifItem

interface GiphyRepositoryPaging {
    fun getGiphy(): PagingData<GifItem>
}