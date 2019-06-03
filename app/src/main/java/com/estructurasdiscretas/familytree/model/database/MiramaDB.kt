package com.estructurasdiscretas.familytree.model.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.provider.BaseColumns
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.DB_NAME
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.DB_VERSION
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_COUPLE
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_PERSON
import com.estructurasdiscretas.familytree.model.database.Mirama.PersonHeaders
import com.estructurasdiscretas.familytree.model.database.Mirama.CoupleHeaders
import com.estructurasdiscretas.familytree.model.database.Mirama.Tables.Companion.TABLE_SON

class MiramaDB(context: Context) :
    SQLiteOpenHelper(context,  DB_NAME, null ,  DB_VERSION) {

    internal interface References {
        companion object {
            val ID_PERSON = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                TABLE_PERSON , Mirama.PersonHeaders.ID)

            val ID_COUPLE = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                TABLE_COUPLE, Mirama.CoupleHeaders.ID)
        }
    }



    @SuppressLint("ObsoleteSdkInt")
    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        if (!db!!.isReadOnly) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                db.setForeignKeyConstraintsEnabled(true)
            } else {
                db.execSQL("PRAGMA foreign_keys=ON")
            }
        }
    }


    override fun onCreate(db: SQLiteDatabase) {

        //PERSON TABLE SQL
        db.execSQL(
            String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL," +
                        "%s TEXT ," + //name
                        "%s TEXT ," + //dad
                        "%s TEXT ," +//mom
                        "%s TEXT ," +//life
                        "%s TEXT ," +//gender
                        "%s TEXT ,"+//birth
                        "%s TEXT)",//img

                TABLE_PERSON, BaseColumns._ID ,
                PersonHeaders.ID,
                PersonHeaders.NAME,
                PersonHeaders.DADLASTNAME,
                PersonHeaders.MOMLASTNAME,
                PersonHeaders.LIFE,
                PersonHeaders.GENDER,
                PersonHeaders.BD,
                PersonHeaders.IMG
            )
        )

        //COUPLE TABLE SQL
        db.execSQL(
            String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL," +
                        "%s TEXT, " +//idMan
                        "%s TEXT, " +//idWoman
                        "%s TEXT, " +
                        "%s TEXT)" ,

                TABLE_COUPLE, BaseColumns._ID ,
                CoupleHeaders.ID,
                CoupleHeaders.MAN,
                CoupleHeaders.WOMAN,
                CoupleHeaders.ID_COUPLE_PARENT_MAN,
                CoupleHeaders.ID_COUPLE_PARENT_WOMAN
            )
        )

        //COUPLE TABLE SQL
        db.execSQL(
            String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL," +
                        "%s TEXT, " +
                        "%s TEXT)" ,

                TABLE_SON, BaseColumns._ID ,
                Mirama.SonHeaders.ID,
                Mirama.SonHeaders.ID_COUPLE,
                Mirama.SonHeaders.ID_COUPLE_SON
            )
        )

        //generetePersons(db)
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_PERSON")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COUPLE")

        onCreate(db)
    }
/*
    fun generetePersons(db: SQLiteDatabase){
        var values : ContentValues = ContentValues()

        values.put(PersonHeaders.NAME,"Cesar")
        values.put(PersonHeaders.DADLASTNAME,"Perez")
        values.put(PersonHeaders.MOMLASTNAME,"Uribe")
        values.put(PersonHeaders.LIFE,true)
        values.put(PersonHeaders.GENDER,"hombre")
        values.put(PersonHeaders.BD,"11/05/1995")
        values.put(PersonHeaders.IMG,"")

        addPerson(db,values)

    }

    fun addPerson(db:SQLiteDatabase,values : ContentValues){
        //SUBTITLES
        db.execSQL(
            String.format(
                "INSERT INTO %s (%s ,%s ,%s ,%s ) VALUES ('%s' ,'%s' ,'%s' ,'%s')",
                TABLE_PERSON,
                PersonHeaders.ID,
                PersonHeaders.NAME,
                PersonHeaders.DADLASTNAME,
                PersonHeaders.MOMLASTNAME,
                PersonHeaders.LIFE,
                PersonHeaders.GENDER,
                PersonHeaders.BD,
                PersonHeaders.IMG,

                values.getAsString(PersonHeaders.ID),
                values.getAsString(PersonHeaders.NAME),
                values.getAsString(PersonHeaders.DADLASTNAME),
                values.getAsString(PersonHeaders.MOMLASTNAME),
                values.getAsString(PersonHeaders.LIFE),
                values.getAsString(PersonHeaders.GENDER),
                values.getAsString(PersonHeaders.BD),
                values.getAsString(PersonHeaders.IMG)
            )
        )
    }

    */

}