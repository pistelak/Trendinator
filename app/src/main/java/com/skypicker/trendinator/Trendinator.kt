package com.skypicker.trendinator

import android.app.Application
import com.skypicker.trendinator.DI.appModule
import com.skypicker.trendinator.DI.netModule
import com.skypicker.trendinator.DI.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Trendinator: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(netModule, appModule, platformModule))
            androidContext(this@Trendinator)
        }
    }

}