package com.debian17.newstestapp.app.di.component

import com.debian17.newstestapp.app.di.module.PresenterModule
import com.debian17.newstestapp.app.ui.news.NewsFragment
import dagger.Component

@Component(modules = [PresenterModule::class])
interface AppComponent {

    fun inject(newsFragment: NewsFragment)

}