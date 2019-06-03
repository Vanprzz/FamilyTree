package com.estructurasdiscretas.familytree.di

import com.estructurasdiscretas.familytree.router.Router
import com.estructurasdiscretas.familytree.ui.modules.main.MainViewModel
import com.estructurasdiscretas.familytree.ui.modules.sessions.splash.SplashViewModel
import com.estructurasdiscretas.familytree.util.preference.Nube
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/* Module of ViewModel abstractions */
val viewModelModule = module {
    viewModel { SplashViewModel(androidApplication(),get()) }
    viewModel { MainViewModel(androidApplication()) }
}



val storage = module {
    single { Nube(androidContext()) }
}


/* Module for manage routes into Application */
val router = module {
    single { Router(androidContext()) }
}
