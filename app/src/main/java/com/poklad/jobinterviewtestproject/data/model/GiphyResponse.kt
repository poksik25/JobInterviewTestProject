package com.poklad.jobinterviewtestproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GiphyResponse(
    @SerializedName("data")
    val data: List<GifItem>
)

@Parcelize
data class GifItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: GifImages
) : Parcelable

@Parcelize
data class GifImages(
    @SerializedName("original")
    val original: GifImageDetails
) : Parcelable

@Parcelize
data class GifImageDetails(
    @SerializedName("url")
    val url: String
) : Parcelable