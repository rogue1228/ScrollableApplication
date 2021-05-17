package com.junhwa.scrollableapplication.application

import android.app.Application
import com.junhwa.scrollableapplication.application.di.dataSourceModule
import com.junhwa.scrollableapplication.application.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(dataSourceModule, repositoryModule)
        }
    }
}