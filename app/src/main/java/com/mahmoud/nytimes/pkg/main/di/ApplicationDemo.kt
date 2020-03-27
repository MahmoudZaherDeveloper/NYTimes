package com.mahmoud.nytimes.pkg.main.di

import android.app.Application
import com.mahmoud.nytimes.pkg.main.ui.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationDemo : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationDemo)
            modules(
                listOf(
                    appModule,
                    mainModule
                )
            )
        }

    }

}