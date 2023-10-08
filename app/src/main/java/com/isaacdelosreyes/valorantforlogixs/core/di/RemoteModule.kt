package com.isaacdelosreyes.valorantforlogixs.core.di

import com.google.gson.GsonBuilder
import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import com.isaacdelosreyes.valorantforlogixs.utils.RETROFIT_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Singleton
    @Provides
    fun retrofitProvider(): Retrofit {
        val builder = Retrofit.Builder().baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return builder.client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun valorantWsProvider(retrofit: Retrofit): ValorantWs {
        return retrofit.create(ValorantWs::class.java)
    }
}