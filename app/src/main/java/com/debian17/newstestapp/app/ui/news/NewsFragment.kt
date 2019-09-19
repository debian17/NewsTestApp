package com.debian17.newstestapp.app.ui.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.debian17.newstestapp.R
import com.debian17.newstestapp.app.App
import com.debian17.newstestapp.app.base.error.NewsErrorHandler
import com.debian17.newstestapp.app.base.hide
import com.debian17.newstestapp.app.base.mvp.BaseFragment
import com.debian17.newstestapp.app.base.show
import com.debian17.newstestapp.app.ui.error.ErrorFragmentDialog
import com.debian17.newstestapp.app.ui.news.adapter.NewsAdapter
import com.debian17.newstestapp.app.ui.news.adapter.NewsDiffCallback
import com.debian17.newstestapp.data.model.Article
import kotlinx.android.synthetic.main.fragment_news.*
import com.debian17.newstestapp.app.base.openBrowser


class NewsFragment : BaseFragment(), NewsView {

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    private lateinit var adapter: NewsAdapter
    private val newsDiffCallback = NewsDiffCallback()

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter(): NewsPresenter {
        val dataComponent = (activity!!.application as App).provideDataComponent()
        return NewsPresenter(dataComponent.provideNewsRepository())
    }

    private val urlClickListener = object : NewsAdapter.UrlClickListener {
        override fun onUrlCLick(url: String) {
            context?.openBrowser(url)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = NewsAdapter(context!!, newsDiffCallback, urlClickListener)

        rvNews.layoutManager = LinearLayoutManager(context)
        rvNews.adapter = adapter

        val itemDecorator = DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
        val drawableDivider = ContextCompat.getDrawable(context!!, R.drawable.shape_divider)
        if (drawableDivider != null) {
            itemDecorator.setDrawable(drawableDivider)
        }
        rvNews.addItemDecoration(itemDecorator)

    }

    override fun showLoading() {
        pbLoading.show()
        rvNews.hide()
    }

    override fun showMain() {
        rvNews.show()
        pbLoading.hide()
    }

    override fun showError(throwable: Throwable) {
        val errorHandler = NewsErrorHandler()
        val errorMessage = errorHandler.getErrorMessage(context!!, throwable)

        val errorFragmentDialog = ErrorFragmentDialog.newInstance(errorMessage)
        errorFragmentDialog.show(childFragmentManager, ErrorFragmentDialog.TAG)

    }

    override fun onNewsLoaded(news: PagedList<Article>) {
        adapter.submitList(news)
    }

    override fun showPagination() {
        adapter.showPagination()
    }

    override fun hidePagination() {
        adapter.hidePagination()
    }

}
