package com.estructurasdiscretas.familytree.model.schema

import java.io.Serializable

data class Couple (
    val id:String,
    val idMan :String,
    val idWoman: String,
    val idCoupleParentMan: String,
    val idCoupleParentWoman: String
): Serializable
