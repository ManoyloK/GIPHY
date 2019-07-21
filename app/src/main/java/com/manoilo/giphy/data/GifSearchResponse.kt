package com.manoilo.giphy.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifSearchResponse(
    @Json(name = "data")
    val gifs: List<GifDto>
)