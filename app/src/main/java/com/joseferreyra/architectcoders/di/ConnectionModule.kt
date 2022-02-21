package com.joseferreyra.architectcoders.di

import com.joseferreyra.architectcoders.networking.ApiStaticInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val connectionModule: Module = module {

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://private-fe87c-simpleclassifieds.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiStaticInterceptor> { ApiStaticInterceptor(androidContext()) }

    single<OkHttpClient> {
        val okHttpClientBuilder: OkHttpClient.Builder =
            OkHttpClient.Builder().addInterceptor(get<ApiStaticInterceptor>())
        okHttpClientBuilder.build()
    }
}