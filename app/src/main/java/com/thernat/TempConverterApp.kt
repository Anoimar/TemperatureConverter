package com.thernat

import android.app.Application
import com.thernat.tempconverter.di.applicationModule
import com.thernat.tempconverter.di.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by m.rafalski on 2019-06-29.
 */
class TempConverterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@TempConverterApp)
            // modules
            modules(listOf(applicationModule, netModule))
        }
    }
}