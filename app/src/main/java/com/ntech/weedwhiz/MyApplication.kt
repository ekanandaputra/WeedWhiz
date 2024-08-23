package com.ntech.weedwhiz

import android.app.Application
import com.ntech.weedwhiz.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}