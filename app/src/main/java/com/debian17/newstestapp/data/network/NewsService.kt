package com.debian17.newstestapp.data.network

import com.debian17.newstestapp.data.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/everything/")
    fun getNews(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Single<NewsResponse>

}