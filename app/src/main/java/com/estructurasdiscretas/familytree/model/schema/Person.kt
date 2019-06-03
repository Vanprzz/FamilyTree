package com.estructurasdiscretas.familytree.model.schema

import java.io.Serializable

data class Person (
    val id: String,
    val name: String,
    val dadLastName: String,
    val momLastName: String,
    val life: String,
    val gender: String,
    val bd: String,
    val img: String
) : Serializable


