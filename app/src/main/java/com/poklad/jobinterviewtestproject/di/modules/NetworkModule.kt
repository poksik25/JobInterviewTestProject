package com.poklad.jobinterviewtestproject.di.modules

import com.poklad.jobinterviewtestproject.data.api.GiphyApi
import com.poklad.jobinterviewtestproject.di.annotations.ApplicationScope
import com.poklad.jobinterviewtestproject.utils.ApiConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    @Provides
    @ApplicationScope
    fun providesStoreApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
        return client.build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}