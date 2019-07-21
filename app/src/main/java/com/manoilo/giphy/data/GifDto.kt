package com.manoilo.giphy.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifDto(
    val id: String,
    val images: ImagesDto
)