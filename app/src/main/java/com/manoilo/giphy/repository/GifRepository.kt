package com.manoilo.giphy.repository

import com.manoilo.giphy.api.GifService
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GifRepository @Inject constructor(
    val gifService: GifService
) {
    fun searchGifs(query: String) = gifService.searchGifs(query).subscribeOn(Schedulers.io())
}