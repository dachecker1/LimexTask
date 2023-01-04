package com.vk.limextask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vk.limextask.domain.interactor.ChannelInteractor
import com.vk.limextask.network.ILimexRestApi
import com.vk.limextask.data.repository.ChannelRepository
import com.vk.limextask.utils.LimexConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { createRetrofitClient().create(ILimexRestApi::class.java) }
    single { getGson() }

    single { ChannelRepository(get(), get()) }
    single { ChannelInteractor(get()) }
}

fun getGson() : Gson = GsonBuilder().setLenient().create()

fun createRetrofitClient() = retrofitClient()

private fun retrofitClient(
    baseUrl : String = LimexConstants.URL.BASE_URL,
    httpClient: OkHttpClient = getInterceptorClient()
) : Retrofit =
    Retrofit.Builder().run {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        build()
    }

private fun getInterceptorClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
}