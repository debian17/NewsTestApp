package com.debian17.newstestapp.app.di.module

import com.debian17.newstestapp.app.source.NewsDataSource
import com.debian17.newstestapp.data.network.NetworkChecker
import com.debian17.newstestapp.data.network.NewsService
import com.debian17.newstestapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    fun provideNewsRepository(
        newsService: NewsService,
        networkChecker: NetworkChecker
    ): NewsDataSource {
        return NewsRepository(newsService, networkChecker)
    }

}