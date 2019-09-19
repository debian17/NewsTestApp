package com.debian17.newstestapp.app.ui.news

import androidx.paging.PagedList
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.debian17.newstestapp.app.base.mvp.BaseView
import com.debian17.newstestapp.data.model.Article

interface NewsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMain()

    fun onNewsLoaded(news: PagedList<Article>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPagination()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hidePagination()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(throwable: Throwable)

}