package com.debian17.newstestapp.app.di.component

import com.debian17.newstestapp.app.di.module.RepositoryModule
import com.debian17.newstestapp.app.source.NewsDataSource
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface DataComponent {

    fun provideNewsRepository(): NewsDataSource

}