package com.estructurasdiscretas.familytree.model.repository.couple

import com.estructurasdiscretas.familytree.model.entity.ErrorItem
import com.estructurasdiscretas.familytree.model.schema.Couple

interface ICoupleRepository{
    fun getCouple(id : String,callback : (cou: Couple?, error : ErrorItem?) -> Unit)
}