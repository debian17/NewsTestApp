package com.debian17.newstestapp.app.ui.news.source

import androidx.paging.DataSource
import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.data.model.Article
import io.reactivex.subjects.PublishSubject

class NewsSourceFactory(
    private val newsDataSource: NewsDataSource,
    private val initialLoading: PublishSubject<Boolean>,
    private val pagination: PublishSubject<Boolean>,
    private val error: PublishSubject<Throwable>
) : DataSource.Factory<Int, Article>() {

    override fun create(): DataSource<Int, Article> {
        return NewsPagedDataSource(newsDataSource, initialLoading, pagination, error)
    }

}