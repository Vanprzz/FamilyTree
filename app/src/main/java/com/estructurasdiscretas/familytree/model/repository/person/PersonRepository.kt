package com.estructurasdiscretas.familytree.model.repository.person

import com.estructurasdiscretas.familytree.model.entity.ErrorItem
import com.estructurasdiscretas.familytree.model.schema.Person

class PersonRepository : IPersonRepository {

    override fun getPerson(id: String, callback: (person: Person?, error: ErrorItem?) -> Unit) {
        //TODO: DataBase dates
    }

}