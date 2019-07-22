package com.manoilo.giphy.utils

import com.manoilo.giphy.data.GifDto
import com.manoilo.giphy.data.GifSearchResponse
import com.manoilo.giphy.data.ImageDto
import com.manoilo.giphy.data.ImagesDto
import io.reactivex.Observable
import retrofit2.Response

object TestUtil {
    fun getGifSearchResponse(): GifSearchResponse {
        val image = ImageDto(
            "https://media1.giphy.com/media/8Ag4AORS8xPYHdeU6f/giphy_s.gif?cid=2801c6cd5d348097564f545467bc4836&rid=giphy_s.gif",
            482, 400
        )
        val gif = GifDto("8Ag4AORS8xPYHdeU6f", ImagesDto(image))
        return GifSearchResponse(listOf(gif))
    }

    fun <T> createSuccesCall(body: T): Observable<Response<T>> {
        return Observable.create { result ->
            result.onNext(
                Response.success(body)
            )
            result.onComplete()
        }
    }
}