package com.estructurasdiscretas.familytree.router

import android.content.Context
import android.content.Intent
import com.estructurasdiscretas.familytree.ui.modules.main.MainActivity

class Router(private val context: Context) {

    fun navigateToMain(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun navigateToProfileDetail(){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }


}