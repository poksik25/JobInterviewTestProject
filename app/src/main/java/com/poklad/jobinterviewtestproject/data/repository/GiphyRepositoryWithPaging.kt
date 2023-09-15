package com.poklad.jobinterviewtestproject.data.repository

import androidx.paging.PagingData
import com.poklad.jobinterviewtestproject.domain.model.GifItem
import com.poklad.jobinterviewtestproject.domain.repository.GiphyRepositoryPaging
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 25

class GiphyRepositoryWithPaging @Inject constructor() : GiphyRepositoryPaging {
    override fun getGiphy(): PagingData<GifItem> {
        TODO("Not yet implemented")
    }
}
