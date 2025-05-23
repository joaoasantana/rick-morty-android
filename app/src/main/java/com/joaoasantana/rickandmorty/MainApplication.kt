package com.joaoasantana.rickandmorty

import android.app.Application
import com.joaoasantana.rickandmorty.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinModules()
    }

    private fun startKoinModules() {
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApplication)

            modules(mainModule)
        }
    }
}
