package com.manoilo.giphy.api


import com.manoilo.giphy.utils.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class GifServiceTest {

    private lateinit var service: GifService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        val client = OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()

        service = Retrofit.Builder()
            .client(client)
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GifService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun searchGifs() {
        enqueueResponse("gifs.json")
        val gifs = service.searchGifs("cast").blockingFirst().body()!!.gifs


        val request = mockWebServer.takeRequest()
        assertThat(request.path.startsWith("/v1/gifs/search"), `is`(true))
        assertThat(request.requestUrl.queryParameterNames().contains("api_key"), `is`(true))

        assertThat(gifs.size, `is`(2))

        val gif = gifs[0]
        assertThat(gif.id, `is`("Ov5NiLVXT8JEc"))

        val image = gif.images.image
        assertThat(image, notNullValue())
        assertThat(
            image.url,
            `is`("https://media2.giphy.com/media/Ov5NiLVXT8JEc/200_s.gif?cid=2801c6cd5d348097564f545467bc4836&rid=200_s.gif")
        )
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
