package com.junhwa.scrollableapplication.application

import android.app.Application
import com.junhwa.scrollableapplication.application.di.dataSourceModule
import com.junhwa.scrollableapplication.application.di.repositoryModule
import com.junhwa.scrollableapplication.application.di.usecaseModule
import com.junhwa.scrollableapplication.application.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApp)
            modules(dataSourceModule, repositoryModule, usecaseModule, viewModelModule)
        }
    }
}