package com.debian17.newstestapp.app.ui.news

import androidx.paging.PagedList
import com.debian17.newstestapp.app.base.mvp.BaseView
import com.debian17.newstestapp.data.model.Article
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface NewsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMain()

    @StateStrategyType(AddToEndStrategy::class)
    fun onNewsLoaded(news: PagedList<Article>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showPagination()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hidePagination()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(throwable: Throwable)

}