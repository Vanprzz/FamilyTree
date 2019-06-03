package com.estructurasdiscretas.familytree.util

import android.content.Context
import android.content.res.Resources

object ResourcesManager {

    /* Resources of the application */
    private lateinit var resources: Resources

    /**
     *
     * @param applicationContext
     */
    fun initialize(applicationContext: Context){
        resources = applicationContext.resources
    }

    /**
     *
     * @param id
     * @return [String]
     */
    fun getString(id: Int) : String = resources.getString(id)




}