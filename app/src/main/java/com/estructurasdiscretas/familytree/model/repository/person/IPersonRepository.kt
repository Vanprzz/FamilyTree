package com.estructurasdiscretas.familytree.model.repository.person

import com.estructurasdiscretas.familytree.model.entity.ErrorItem
import com.estructurasdiscretas.familytree.model.schema.Person

interface IPersonRepository{
    fun getPerson(id : String,callback : (person: Person?, error : ErrorItem?) -> Unit)
}