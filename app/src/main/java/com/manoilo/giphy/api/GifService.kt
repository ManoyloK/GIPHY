package com.manoilo.giphy.api

import com.manoilo.giphy.data.GifSearchResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {
    @GET("v1/gifs/search")
    fun searchGifs(@Query("q") query: String): Observable<Response<GifSearchResponse>>

}