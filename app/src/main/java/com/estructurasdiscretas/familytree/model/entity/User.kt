package com.estructurasdiscretas.familytree.model.entity

import com.estructurasdiscretas.familytree.model.schema.Person
import com.estructurasdiscretas.familytree.util.preference.Nube
import com.google.gson.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.google.gson.reflect.TypeToken


object User : KoinComponent{

    private val nube: Nube by inject()

    fun savePerson(person: Person) {
        val json = Gson().toJson(person)

        nube.saveStringValue("user", json)
    }

    fun getPerson(): Person? {
        val json = nube.getStringValue("person")

        return if (!json.isNullOrEmpty()) {
            val userType = object : TypeToken<User>() {

            }.type
            Gson().fromJson<Person>(json, userType)

        } else {
            null
        }
    }


    fun logout() {
        nube.removeValue("person")
    }

    enum class Values {
        PASSWORD, TOKEN
    }



}