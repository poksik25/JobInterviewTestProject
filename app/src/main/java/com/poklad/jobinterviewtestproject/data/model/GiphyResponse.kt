package com.poklad.jobinterviewtestproject.data.model

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val data: List<GifItem>
)

data class GifItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: GifImages
)

data class GifImages(
    @SerializedName("original")
    val original: GifImageDetails
)

data class GifImageDetails(
    @SerializedName("url")
    val url: String
)