package com.estructurasdiscretas.familytree.model.database

import android.content.ContentValues
import android.database.Cursor
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.estructurasdiscretas.familytree.model.database.Mirama.*
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_COUPLE
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_PERSON
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_SON
import com.estructurasdiscretas.familytree.model.schema.Couple
import com.estructurasdiscretas.familytree.model.schema.Person
import com.estructurasdiscretas.familytree.model.schema.Son

class MiramaManageDB(context: Context) {

    private var dataBase: MiramaDB? = MiramaDB(context)

    fun getDb(): SQLiteDatabase { return dataBase!!.writableDatabase }

    // |||||||||||OPC PERSON |||||||||||

    private val tableUser = TABLE_PERSON
    private val proyUser = arrayOf(
        PersonHeaders.ID,
        PersonHeaders.NAME,
        PersonHeaders.DADLASTNAME,
        PersonHeaders.MOMLASTNAME,
        PersonHeaders.LIFE,
        PersonHeaders.GENDER,
        PersonHeaders.BD,
        PersonHeaders.IMG
    )

    fun getPersons(selection: String, selectionArgs: Array<String>): MutableList<Person>? {
        val db = dataBase!!.readableDatabase
        val personList: MutableList<Person> = mutableListOf<Person>()
        try {
            db.beginTransaction()

            val c: Cursor =
                db.query(tableUser, proyUser, selection, selectionArgs, null, null, PersonHeaders.NAME + " ASC")

            while (c.moveToNext()) {

                personList.add(
                    Person(
                        c.getString(c.getColumnIndex(PersonHeaders.ID)),
                        c.getString(c.getColumnIndex(PersonHeaders.NAME)),
                        c.getString(c.getColumnIndex(PersonHeaders.DADLASTNAME)),
                        c.getString(c.getColumnIndex(PersonHeaders.MOMLASTNAME)),
                        c.getString(c.getColumnIndex(PersonHeaders.LIFE)),
                        c.getString(c.getColumnIndex(PersonHeaders.GENDER)),
                        c.getString(c.getColumnIndex(PersonHeaders.BD)),
                        c.getString(c.getColumnIndex(PersonHeaders.IMG)
                        )
                    )
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return personList
    }

    fun getPerson(selection: String, selectionArgs: Array<String>): Person? {
        val db = dataBase!!.readableDatabase
        var person: Person? = null
        try {
            db.beginTransaction()

            val c: Cursor = db.query(tableUser, proyUser, selection, selectionArgs, null, null, null)

            while (c.moveToNext()) {

                person = Person(
                    c.getString(c.getColumnIndex(PersonHeaders.ID)),
                    c.getString(c.getColumnIndex(PersonHeaders.NAME)),
                    c.getString(c.getColumnIndex(PersonHeaders.DADLASTNAME)),
                    c.getString(c.getColumnIndex(PersonHeaders.MOMLASTNAME)),
                    c.getString(c.getColumnIndex(PersonHeaders.LIFE)),
                    c.getString(c.getColumnIndex(PersonHeaders.GENDER)),
                    c.getString(c.getColumnIndex(PersonHeaders.BD)),
                    c.getString(c.getColumnIndex(PersonHeaders.IMG))
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return person
    }

    fun insertPerson(person: Person): Boolean {

        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(PersonHeaders.ID, person.id)
        values.put(PersonHeaders.NAME, person.name)
        values.put(PersonHeaders.DADLASTNAME, person.dadLastName)
        values.put(PersonHeaders.MOMLASTNAME, person.momLastName)
        values.put(PersonHeaders.LIFE, person.life)
        values.put(PersonHeaders.GENDER, person.gender)
        values.put(PersonHeaders.BD, person.bd)
        values.put(PersonHeaders.IMG, person.img)

        val result = db.insertOrThrow(tableUser, null, values)
        return result > 0
    }

    fun updatePerson(newUser: Person): Boolean {
        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(PersonHeaders.NAME, newUser.name)
        values.put(PersonHeaders.DADLASTNAME, newUser.dadLastName)
        values.put(PersonHeaders.MOMLASTNAME, newUser.momLastName)
        values.put(PersonHeaders.LIFE, newUser.life)
        values.put(PersonHeaders.GENDER, newUser.gender)
        values.put(PersonHeaders.BD, newUser.bd)
        values.put(PersonHeaders.IMG, newUser.img)

        val whereClause = String.format("%s=?", PersonHeaders.ID)
        val whereArgs = arrayOf(newUser.id)
        val result = db.update(tableUser, values, whereClause, whereArgs)


        return result > 0
    }

    fun deletePerson(idUser: String) {
        val db = dataBase!!.writableDatabase
        val whereClause = PersonHeaders.ID + "=?"
        val whereArgs = arrayOf(idUser)
        //val result = db.delete(TABLE_USERS, whereClause, whereArgs)
        db.beginTransaction()

        val where: String = PersonHeaders.ID + "='" + idUser + "'"
        db.execSQL("PRAGMA foreign_keys = ON")
        db.execSQL(" DELETETABLE_$TABLE_PERSON  WHERE $where ")
        db.endTransaction()
    }
    // ||||||||||| END OPC PERSON |||||||||||


    // |||||||||||OPC COUPLE |||||||||||

    private val tableCouple = TABLE_COUPLE
    private val proyCouple = arrayOf(
        CoupleHeaders.ID,
        CoupleHeaders.MAN,
        CoupleHeaders.WOMAN,
        CoupleHeaders.ID_COUPLE_PARENT_MAN,
        CoupleHeaders.ID_COUPLE_PARENT_WOMAN
    )

    fun getCouples(selection: String, selectionArgs: Array<String>): MutableList<Couple> {
        val db = dataBase!!.readableDatabase
        val coupleList: MutableList<Couple> = mutableListOf()
        try {
            db.beginTransaction()

            val c: Cursor =
                db.query(tableCouple, proyCouple, selection, selectionArgs, null, null, null)

            while (c.moveToNext()) {
                coupleList.add(
                    Couple(
                        c.getString(c.getColumnIndex(CoupleHeaders.ID)),
                        c.getString(c.getColumnIndex(CoupleHeaders.MAN)),
                        c.getString(c.getColumnIndex(CoupleHeaders.WOMAN)),
                        c.getString(c.getColumnIndex(CoupleHeaders.ID_COUPLE_PARENT_MAN)),
                        c.getString(c.getColumnIndex(CoupleHeaders.ID_COUPLE_PARENT_WOMAN))
                    )
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return coupleList
    }

    fun getCouple(selection: String, selectionArgs: Array<String>): Couple? {
        val db = dataBase!!.readableDatabase
        var couple: Couple? = null
        try {
            db.beginTransaction()

            val c: Cursor = db.query(tableCouple, proyCouple, selection, selectionArgs, null, null, null)

            while (c.moveToNext()) {

                couple = Couple(
                    c.getString(c.getColumnIndex(CoupleHeaders.ID)),
                    c.getString(c.getColumnIndex(CoupleHeaders.MAN)),
                    c.getString(c.getColumnIndex(CoupleHeaders.WOMAN)),
                    c.getString(c.getColumnIndex(CoupleHeaders.ID_COUPLE_PARENT_MAN)),
                    c.getString(c.getColumnIndex(CoupleHeaders.ID_COUPLE_PARENT_WOMAN))
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return couple
    }

    fun insertCouple(couple: Couple): Boolean {

        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(CoupleHeaders.ID, couple.id)
        values.put(CoupleHeaders.MAN, couple.idMan)
        values.put(CoupleHeaders.WOMAN, couple.idWoman)
        values.put(CoupleHeaders.ID_COUPLE_PARENT_MAN, couple.idCoupleParentMan)
        values.put(CoupleHeaders.ID_COUPLE_PARENT_WOMAN, couple.idCoupleParentWoman)

        val result = db.insertOrThrow(tableCouple, null, values)
        return result > 0
    }

    fun updateCouple(newCouple: Couple): Boolean {
        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(CoupleHeaders.MAN, newCouple.idMan)
        values.put(CoupleHeaders.WOMAN, newCouple.idWoman)
        values.put(CoupleHeaders.ID_COUPLE_PARENT_MAN, newCouple.idCoupleParentMan)
        values.put(CoupleHeaders.ID_COUPLE_PARENT_WOMAN, newCouple.idCoupleParentWoman)

        val whereClause = String.format("%s=?", PersonHeaders.ID)
        val whereArgs = arrayOf(newCouple.id)
        val result = db.update(tableCouple, values, whereClause, whereArgs)


        return result > 0
    }

    fun deleteCouple(idCuple: String) {
        val db = dataBase!!.writableDatabase
        val whereClause = CoupleHeaders.ID + "=?"
        val whereArgs = arrayOf(idCuple)
        //val result = db.delete(TABLE_USERS, whereClause, whereArgs)
        db.beginTransaction()

        val where: String = CoupleHeaders.ID + "='" + idCuple + "'"
        db.execSQL("PRAGMA foreign_keys = ON")
        db.execSQL(" DELETETABLE_$TABLE_COUPLE  WHERE $where ")
        db.endTransaction()
    }
    // ||||||||||| END OPC COUPLE |||||||||||

    // |||||||||||OPC COUPLE |||||||||||

    private val tableSon = TABLE_SON
    private val proySon = arrayOf(
        SonHeaders.ID,
        SonHeaders.ID_COUPLE,
        SonHeaders.ID_COUPLE_SON
    )

    fun getSons(selection: String, selectionArgs: Array<String>): MutableList<Son> {
        val db = dataBase!!.readableDatabase
        val sonList: MutableList<Son> = mutableListOf()
        try {
            db.beginTransaction()

            val c: Cursor =
                db.query(tableSon, proySon, selection, selectionArgs, null, null, null)

            while (c.moveToNext()) {
                sonList.add(
                    Son(
                        c.getString(c.getColumnIndex(SonHeaders.ID)),
                        c.getString(c.getColumnIndex(SonHeaders.ID_COUPLE)),
                        c.getString(c.getColumnIndex(SonHeaders.ID_COUPLE_SON))
                    )
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return sonList
    }

    fun getSon(selection: String, selectionArgs: Array<String>): Son? {
        val db = dataBase!!.readableDatabase
        var son: Son? = null
        try {
            db.beginTransaction()

            val c: Cursor = db.query(tableSon, proySon, selection, selectionArgs, null, null, null)

            while (c.moveToNext()) {
                son = Son(
                    c.getString(c.getColumnIndex(SonHeaders.ID)),
                    c.getString(c.getColumnIndex(SonHeaders.ID_COUPLE)),
                    c.getString(c.getColumnIndex(SonHeaders.ID_COUPLE_SON))
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return son
    }

    fun insertCouple(son: Son): Boolean {

        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(SonHeaders.ID, son.id)
        values.put(SonHeaders.ID_COUPLE, son.idCouple)
        values.put(SonHeaders.ID_COUPLE_SON, son.idCoupleSon)

        val result = db.insertOrThrow(tableSon, null, values)
        return result > 0
    }

    fun updateCouple(newSon: Son): Boolean {
        val db = dataBase!!.writableDatabase
        val values = ContentValues()

        values.put(SonHeaders.ID, newSon.id)
        values.put(SonHeaders.ID_COUPLE, newSon.idCouple)
        values.put(SonHeaders.ID_COUPLE_SON, newSon.idCoupleSon)

        val whereClause = String.format("%s=?", PersonHeaders.ID)
        val whereArgs = arrayOf(newSon.id)
        val result = db.update(tableSon, values, whereClause, whereArgs)


        return result > 0
    }

    fun deleteSon(idSon: String) {
        val db = dataBase!!.writableDatabase
        val whereClause = CoupleHeaders.ID + "=?"
        val whereArgs = arrayOf(idSon)
        //val result = db.delete(TABLE_USERS, whereClause, whereArgs)
        db.beginTransaction()

        val where: String = CoupleHeaders.ID + "='" + idSon + "'"
        db.execSQL("PRAGMA foreign_keys = ON")
        db.execSQL(" DELETETABLE_$TABLE_COUPLE  WHERE $where ")
        db.endTransaction()
    }
    // ||||||||||| END OPC PERSON |||||||||||

}