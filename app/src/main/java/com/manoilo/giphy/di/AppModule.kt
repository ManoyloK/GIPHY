package com.manoilo.giphy.di

import com.manoilo.giphy.BuildConfig
import com.manoilo.giphy.api.GifService
import com.manoilo.giphy.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGifService(): GifService {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GifService::class.java)
    }
}
