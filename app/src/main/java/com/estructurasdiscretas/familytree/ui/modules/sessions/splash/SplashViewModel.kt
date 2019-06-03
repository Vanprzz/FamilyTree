package com.estructurasdiscretas.familytree.ui.modules.sessions.splash

import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estructurasdiscretas.familytree.model.database.Mirama
import com.estructurasdiscretas.familytree.model.database.MiramaManageDB
import com.estructurasdiscretas.familytree.model.schema.Couple
import com.estructurasdiscretas.familytree.model.schema.Person
import com.estructurasdiscretas.familytree.model.schema.Son
import com.estructurasdiscretas.familytree.router.Router

class SplashViewModel(
    private val context: Context,
    private val router: Router
) : ViewModel() {

    private var manageMiramaDB : MiramaManageDB = MiramaManageDB(context)

    /* Mutable Live Data */
    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = true }

    /* Exposed Live Data */
    val loading: LiveData<Boolean> get() = _loading

    /* ViewModel associated to this activity */
    private val TIME_SPLASH = 3000

    fun checkLog() {
        _loading.value = true
        genereteDataBase()
        Handler().postDelayed( {
            router.navigateToMain()
        },TIME_SPLASH.toLong() )
    }

    fun genereteDataBase(){
        val id= context.getSharedPreferences("Mirama", Context.MODE_PRIVATE).getString("idCoupleStart",null)
        if(id == null){

            val Iam : String = Mirama.IdPerson.generatePersonID()
            val Sister : String = Mirama.IdPerson.generatePersonID()
            val Bro : String = Mirama.IdPerson.generatePersonID()

            manageMiramaDB.insertPerson(Person(Iam,"Cesar","Perez","Uribe","ok","hombre","11/05/1995","https://www.facebook.com/photo.php?fbid=2233636013323276&set=a.149465858406979&type=3&theater"))
            manageMiramaDB.insertPerson(Person(Bro,"Hugo","Perez","Uribe","ok","hombre","11/05/1995","https://www.facebook.com/photo.php?fbid=2233636013323276&set=a.149465858406979&type=3&theater"))
            manageMiramaDB.insertPerson(Person(Sister,"Jessica","Perez","Uribe","ok","hombre","11/05/1995","https://www.facebook.com/photo.php?fbid=2233636013323276&set=a.149465858406979&type=3&theater"))

            val idDad = Mirama.IdPerson.generatePersonID()
            val idGDadM = Mirama.IdPerson.generatePersonID()
            val idGDadW = Mirama.IdPerson.generatePersonID()

            val idMom = Mirama.IdPerson.generatePersonID()
            val idGMomM = Mirama.IdPerson.generatePersonID()
            val idGMomW = Mirama.IdPerson.generatePersonID()


            manageMiramaDB.insertPerson(Person(idMom,"Maria","Uribe","Trejo","ok","woman","20/05/1970",""))
            manageMiramaDB.insertPerson(Person(idDad,"Juan","Perez","Alvarez","ok","man","09/07/1964",""))

            manageMiramaDB.insertPerson(Person(idGMomW,"Isabel","Trejo","Guzman","ok","woman","20/05/1970",""))
            manageMiramaDB.insertPerson(Person(idGMomM,"Santiago","Uribe","Resendiz","ok","man","09/07/1964",""))

            manageMiramaDB.insertPerson(Person(idGDadW,"Tere","Alvarez","Rincon","ok","woman","20/05/1970",""))
            manageMiramaDB.insertPerson(Person(idGDadM,"Teodoro","Perez","Diaz","ok","man","09/07/1964",""))


            val idCoupleStart = Mirama.IdCouple.generateCoupleID()
            val idCoupleStart1 = Mirama.IdCouple.generateCoupleID()
            val idCoupleStart2 = Mirama.IdCouple.generateCoupleID()
            val idDads = Mirama.IdCouple.generateCoupleID()
            val idGParentsM = Mirama.IdCouple.generateCoupleID()
            val idGParentsW = Mirama.IdCouple.generateCoupleID()


            manageMiramaDB.insertCouple(Couple(idCoupleStart,Iam,"",idDads,""))
            manageMiramaDB.insertCouple(Couple(idCoupleStart1,"",Sister,"",idDads))
            manageMiramaDB.insertCouple(Couple(idCoupleStart2,Bro,"",idDads,""))


            manageMiramaDB.insertCouple(Couple(idDads,idDad,idMom,idGParentsM,idGParentsW))
            manageMiramaDB.insertCouple(Couple(idGParentsM,idGDadM,idGDadW,"",""))
            manageMiramaDB.insertCouple(Couple(idGParentsW,idGMomM,idGMomW,"",""))


            manageMiramaDB.insertCouple(Son(Mirama.IdSon.generateSonID(),idDads,Iam))
            manageMiramaDB.insertCouple(Son(Mirama.IdSon.generateSonID(),idDads,Bro))
            manageMiramaDB.insertCouple(Son(Mirama.IdSon.generateSonID(),idDads,Sister))

            manageMiramaDB.insertCouple(Son(Mirama.IdSon.generateSonID(),idGParentsM,idDads))
            manageMiramaDB.insertCouple(Son(Mirama.IdSon.generateSonID(),idGParentsW,idDads))



            val prefs = context.getSharedPreferences("Mirama", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("idCoupleStart",idCoupleStart)
            editor.apply()
        }

    }




}