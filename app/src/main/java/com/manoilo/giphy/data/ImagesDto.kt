package com.manoilo.giphy.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesDto(

    @Json(name = "fixed_height_still")
    val image: ImageDto
)