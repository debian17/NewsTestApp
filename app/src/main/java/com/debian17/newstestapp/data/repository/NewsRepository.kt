package com.debian17.newstestapp.data.repository

import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.data.model.Article
import com.debian17.newstestapp.data.network.NetworkChecker
import com.debian17.newstestapp.data.network.NewsService
import io.reactivex.Single

class NewsRepository(
    private val newsService: NewsService,
    private val networkChecker: NetworkChecker
) : NewsDataSource {

    override fun getNews(pageSize: Int, page: Int): Single<List<Article>> {
        return networkChecker.prepareRequest(newsService.getNews(pageSize, page))
            .map { it.news }
    }

}