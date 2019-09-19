package com.debian17.newstestapp.app.source

import com.debian17.newstestapp.data.model.Article
import io.reactivex.Single

interface NewsDataSource {

    fun getNews(pageSize: Int, page: Int): Single<List<Article>>

}