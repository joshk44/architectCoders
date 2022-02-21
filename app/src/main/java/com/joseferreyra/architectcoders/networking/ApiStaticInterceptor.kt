package com.joseferreyra.architectcoders.networking

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class ApiStaticInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}