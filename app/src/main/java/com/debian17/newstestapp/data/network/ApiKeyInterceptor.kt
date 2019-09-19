package com.debian17.newstestapp.data.network

import com.debian17.newstestapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "apiKey"
        private const val QUERY = "q"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val url = request.url()

        val newUrl = url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .addQueryParameter(QUERY, BuildConfig.NEWS_TYPE)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

}