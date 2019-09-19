package com.debian17.newstestapp.app.ui.news

import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arellomobile.mvp.InjectViewState
import com.debian17.newstestapp.app.base.mvp.BasePresenter
import com.debian17.newstestapp.app.base.observeOnUI
import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.app.ui.news.source.NewsSourceFactory
import com.debian17.newstestapp.data.model.Article
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executors

@InjectViewState
class NewsPresenter(newsDataSource: NewsDataSource) : BasePresenter<NewsView>() {

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 1
    }

    private val initialLoading: PublishSubject<Boolean> = PublishSubject.create()
    private val pagination: PublishSubject<Boolean> = PublishSubject.create()
    private val error: PublishSubject<Throwable> = PublishSubject.create()

    private val newsSourceFactory = NewsSourceFactory(
        newsDataSource,
        initialLoading,
        pagination,
        error
    )

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    private val news = LivePagedListBuilder(newsSourceFactory, config)
        .setFetchExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val newsObserver = Observer<PagedList<Article>> {
        viewState.onNewsLoaded(it)
    }

    override fun onFirstViewAttach() {

        unsubscribeOnDestroy(initialLoading.observeOnUI().subscribe(this::handleInitialLoading))
        unsubscribeOnDestroy(pagination.observeOnUI().subscribe(this::handlePagination))
        unsubscribeOnDestroy(error.observeOnUI().subscribe(this::handleError))

        news.observeForever(newsObserver)

    }

    private fun handleInitialLoading(isInitialLoading: Boolean) {
        if (isInitialLoading) {
            viewState.showLoading()
        } else {
            viewState.showMain()
        }
    }

    private fun handlePagination(isPagination: Boolean) {
        if (isPagination) {
            viewState.showPagination()
        } else {
            viewState.hidePagination()
        }
    }

    private fun handleError(throwable: Throwable) {
        viewState.showError(throwable)
    }

    override fun onDestroy() {
        super.onDestroy()
        news.removeObserver(newsObserver)
    }

}