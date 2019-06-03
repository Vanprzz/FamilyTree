package com.estructurasdiscretas.familytree.ui.modules.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estructurasdiscretas.familytree.model.database.Mirama
import com.estructurasdiscretas.familytree.model.database.MiramaManageDB
import com.estructurasdiscretas.familytree.model.schema.Couple
import com.estructurasdiscretas.familytree.model.schema.Person

class MainViewModel (private val context : Context) : ViewModel(){

    private var manageMiramaDB : MiramaManageDB = MiramaManageDB(context)

    private val _couple: MutableLiveData<Couple> = MutableLiveData<Couple>()
    val couple: LiveData<Couple> get() = _couple

    private val _PersonStart: MutableLiveData<Pair<Person?,Person?>> = MutableLiveData()
    val personStart: LiveData<Pair<Person?,Person?>> get() = _PersonStart

    private val _PersonAB: MutableLiveData<Pair<Person?,Person?>> = MutableLiveData()
    val personAB: LiveData<Pair<Person?,Person?>> get() = _PersonAB

    private val _PersonCD: MutableLiveData<Pair<Person?,Person?>> = MutableLiveData()
    val personCD: LiveData<Pair<Person?,Person?>> get() = _PersonCD

    private val _listSon: MutableLiveData< List< Pair<Person?,Person?> > > = MutableLiveData()
    val listSon: LiveData<List< Pair<Person?,Person?> >> get() = _listSon

    fun getParents(idCoupleStart:String){
        _couple.value = manageMiramaDB.getCouple(Mirama.PersonHeaders.ID+"=?",arrayOf(idCoupleStart))
        val personStartMan =try {
             manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(_couple.value!!.idMan))
        }catch (e:KotlinNullPointerException){null}

        val personStartWoman =try {
             manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(_couple.value!!.idWoman))
        }catch (e:KotlinNullPointerException){null}

        _PersonStart.value = Pair(personStartMan,personStartWoman)

        try{
            val coupleAB = manageMiramaDB.getCouple(Mirama.PersonHeaders.ID+"=?",arrayOf((_couple.value)!!.idCoupleParentMan))

            val personABMan = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(coupleAB!!.idMan))
            val personABWoman = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(coupleAB!!.idWoman))

            _PersonAB.value = Pair(personABMan,personABWoman)
        }catch(e: KotlinNullPointerException){
            _PersonAB.value = Pair(null,null)
        }

        try{
            val coupleCD = manageMiramaDB.getCouple(Mirama.PersonHeaders.ID+"=?",arrayOf((_couple.value)!!.idCoupleParentWoman))

            val personCDMan = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(coupleCD!!.idMan))
            val personCDWoman = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(coupleCD!!.idWoman))

            _PersonCD.value = Pair(personCDMan,personCDWoman)
        }catch(e: KotlinNullPointerException){
            _PersonCD.value = Pair(null,null)
        }


        val listSon = mutableListOf<Pair<Person?,Person?>>()
        val son = manageMiramaDB.getSons(Mirama.SonHeaders.ID_COUPLE+"=?",arrayOf( (_couple.value)!!.id))

        var personMan : Person?= null
        var personWoman: Person? = null
        for(sonItem in son){

            try {
                personMan = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(sonItem.idCoupleSon))
            }catch (e : KotlinNullPointerException){}
            try {
                personWoman = manageMiramaDB.getPerson(Mirama.PersonHeaders.ID+"=?",arrayOf(sonItem.idCoupleSon))
            }catch (e :KotlinNullPointerException){ }

            listSon.add(Pair(personMan,personWoman))
        }



        _listSon.value = listSon.toMutableList()
    }

}