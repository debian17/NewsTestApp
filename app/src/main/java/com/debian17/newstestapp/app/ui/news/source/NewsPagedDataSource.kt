package com.debian17.newstestapp.app.ui.news.source

import com.debian17.newstestapp.app.base.recycler.PaginationDataSource
import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.data.model.Article
import io.reactivex.subjects.PublishSubject

class NewsPagedDataSource(
    private val newsDataSource: NewsDataSource,
    initialLoading: PublishSubject<Boolean>,
    pagination: PublishSubject<Boolean>,
    error: PublishSubject<Throwable>
) : PaginationDataSource<Int, Article>(initialLoading, pagination, error) {

    private var page = 1

    override fun loadInitialData(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        val news = getNews(params.requestedLoadSize, page)
        page++
        callback.onResult(news, null, page)
    }

    override fun loadDataAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        val news = getNews(params.requestedLoadSize, page)
        page++
        callback.onResult(news, page)
    }

    private fun getNews(pageSize: Int, page: Int): List<Article> {
        return newsDataSource.getNews(pageSize, page).blockingGet()
    }

}