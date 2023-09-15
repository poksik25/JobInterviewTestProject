package com.poklad.jobinterviewtestproject.data.mapper

import com.poklad.jobinterviewtestproject.data.model.GifItemResponse
import com.poklad.jobinterviewtestproject.domain.model.GifItem
import com.poklad.jobinterviewtestproject.utils.Mapper
import javax.inject.Inject

class GifItemResponseToGifItemMapper @Inject constructor() : Mapper<GifItemResponse, GifItem> {
    override fun map(data: GifItemResponse): GifItem {
        return GifItem(
            id = data.id,
            imageUrl = data.images.original.url
        )
    }
}