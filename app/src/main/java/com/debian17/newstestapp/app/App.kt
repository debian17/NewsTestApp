package com.debian17.newstestapp.app

import android.app.Application
import com.debian17.newstestapp.app.di.component.AppComponent
import com.debian17.newstestapp.app.di.component.DaggerAppComponent
import com.debian17.newstestapp.app.di.component.DaggerDataComponent
import com.debian17.newstestapp.app.di.component.DataComponent
import com.debian17.newstestapp.app.di.module.ContextModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var dataComponent: DataComponent
    }

    override fun onCreate() {
        super.onCreate()

        val contextModule = ContextModule(this)

        appComponent = DaggerAppComponent.builder()
            .contextModule(contextModule)
            .build()

        dataComponent = DaggerDataComponent.builder()
            .contextModule(contextModule)
            .build()

    }

}