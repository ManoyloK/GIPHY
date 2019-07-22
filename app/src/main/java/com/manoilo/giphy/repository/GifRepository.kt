package com.manoilo.giphy.repository

import com.manoilo.giphy.api.GifService
import com.manoilo.giphy.data.GifSearchResponse
import com.manoilo.giphy.testing.OpenForTesting
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

@OpenForTesting
class GifRepository @Inject constructor(
    val gifService: GifService
) {
    fun searchGifs(query: String): Observable<Response<GifSearchResponse>> {
        return gifService.searchGifs(query).subscribeOn(Schedulers.io())
    }
}