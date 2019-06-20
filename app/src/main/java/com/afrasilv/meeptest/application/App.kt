package com.afrasilv.meeptest.application

import android.app.Application
import com.afrasilv.meeptest.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}