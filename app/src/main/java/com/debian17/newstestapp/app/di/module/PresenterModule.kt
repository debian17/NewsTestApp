package com.debian17.newstestapp.app.di.module

import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.app.ui.news.NewsPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class PresenterModule {

    @Provides
    fun provideNewsPresenter(newsDataSource: NewsDataSource): NewsPresenter {
        return NewsPresenter(newsDataSource)
    }

}