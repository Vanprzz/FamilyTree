package com.estructurasdiscretas.familytree

import android.app.Application
import com.estructurasdiscretas.familytree.di.router
import com.estructurasdiscretas.familytree.di.storage
import com.estructurasdiscretas.familytree.di.viewModelModule
import com.estructurasdiscretas.familytree.util.ResourcesManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application(){

    override fun onCreate() {
        super.onCreate()
        ResourcesManager.initialize(applicationContext)

        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(viewModelModule, router, storage)
        }

    }


}