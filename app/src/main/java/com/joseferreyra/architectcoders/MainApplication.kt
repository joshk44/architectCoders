package com.joseferreyra.architectcoders

import android.app.Application
import com.joseferreyra.architectcoders.di.connectionModule
import com.joseferreyra.architectcoders.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
        fun get(): MainApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(mainModule, connectionModule))
        }
    }
}