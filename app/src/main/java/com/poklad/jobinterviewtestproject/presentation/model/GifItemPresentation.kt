package com.poklad.jobinterviewtestproject.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifItemPresentation(
    val id: String,
    val imageUrl: String
) : Parcelable