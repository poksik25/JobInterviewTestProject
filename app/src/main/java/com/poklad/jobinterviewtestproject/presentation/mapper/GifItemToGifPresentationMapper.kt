package com.poklad.jobinterviewtestproject.presentation.mapper

import com.poklad.jobinterviewtestproject.domain.model.GifItem
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.utils.Mapper
import javax.inject.Inject

class GifItemToGifPresentationMapper @Inject constructor() : Mapper<GifItem, GifItemPresentation> {
    override fun map(data: GifItem): GifItemPresentation {
        return GifItemPresentation(
            id = data.id,
            imageUrl = data.imageUrl
        )
    }
}