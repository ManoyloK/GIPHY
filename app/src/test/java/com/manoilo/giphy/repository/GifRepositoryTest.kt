package com.manoilo.giphy.repository

import com.manoilo.giphy.api.GifService
import com.manoilo.giphy.data.GifSearchResponse
import com.manoilo.giphy.utils.TestUtil
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Response

@RunWith(JUnit4::class)
class GifRepositoryTest {
    private val githubService = mock(GifService::class.java)
    private val repository = GifRepository(githubService)

    @Test
    fun searchGifs() {
        val gifSearchResponse = TestUtil.getGifSearchResponse()
        val sourceData: Observable<Response<GifSearchResponse>> = TestUtil.createSuccesCall(gifSearchResponse)

        `when`(githubService.searchGifs("cats")).thenReturn(sourceData)
        val searchGifs = repository.searchGifs("cats")
        verify(githubService, times(1)).searchGifs("cats")
        val response = searchGifs.blockingFirst()
        assertThat(response.isSuccessful, `is`(true))
        assertThat(response.body(), `is`(gifSearchResponse))
    }

}