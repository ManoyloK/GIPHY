package com.manoilo.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDto(
    val url: String,
    val width: Int,
    val height: Int
)