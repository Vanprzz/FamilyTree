package com.estructurasdiscretas.familytree.ui.modules.sessions.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.estructurasdiscretas.familytree.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewmodel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        viewmodel.checkLog()

        observeViewModel()
    }

    /**
     *  Observers
     */
    private fun observeViewModel(){
        //viewmodel.loading.observe(this, Observer<Boolean> { progressBar(it) })
    }



}
