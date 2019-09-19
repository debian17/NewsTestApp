package com.debian17.newstestapp.app.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.debian17.newstestapp.data.network.ApiKeyInterceptor
import com.debian17.newstestapp.data.network.NetworkChecker
import com.debian17.newstestapp.data.network.NewsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [ContextModule::class])
class NetworkModule {

    companion object {
        private const val NEWS_API_URL = "https://newsapi.org/"
        private const val TIME_OUT = 30L
    }

    @Provides
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkChecker(connectivityManager: ConnectivityManager): NetworkChecker {
        return NetworkChecker(connectivityManager)
    }

    @Provides
    fun provideNewsRetrofit(): Retrofit {

        val apiKeyInterceptor = ApiKeyInterceptor()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(apiKeyInterceptor)
            .build()

        val gsonConverterFactory = GsonConverterFactory.create()
        val rxJavaCallAdapter = RxJava2CallAdapterFactory.create()

        return Retrofit.Builder()
            .baseUrl(NEWS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJavaCallAdapter)
            .build()
    }

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}