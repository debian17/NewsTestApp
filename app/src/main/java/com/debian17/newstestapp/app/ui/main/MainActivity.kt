package com.debian17.newstestapp.app.ui.main

import android.os.Bundle
import com.debian17.newstestapp.R
import com.debian17.newstestapp.app.base.mvp.BaseActivity
import com.debian17.newstestapp.app.ui.navigation.MainNavigator
import com.debian17.newstestapp.app.ui.navigation.MainNavigatorProvider
import com.debian17.newstestapp.app.ui.news.NewsFragment

class MainActivity : BaseActivity(), MainNavigatorProvider {

    private lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNavigator = MainNavigator(supportFragmentManager)

        if (savedInstanceState == null) {
            val newsFragment = NewsFragment.newInstance()
            mainNavigator.addFragment(newsFragment, false)
        }

    }

    override fun provideMainNavigator(): MainNavigator {
        return mainNavigator
    }

}
