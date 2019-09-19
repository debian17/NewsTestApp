package com.debian17.newstestapp.app

import android.app.Application
import com.debian17.newstestapp.app.di.component.DaggerDataComponent
import com.debian17.newstestapp.app.di.component.DataComponent
import com.debian17.newstestapp.app.di.module.ContextModule

class App : Application() {

    private lateinit var dataComponent: DataComponent

    override fun onCreate() {
        super.onCreate()

        val contextModule = ContextModule(this)

        dataComponent = DaggerDataComponent.builder()
            .contextModule(contextModule)
            .build()

    }

    fun provideDataComponent(): DataComponent {
        return dataComponent
    }

}