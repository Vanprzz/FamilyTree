package com.estructurasdiscretas.familytree.util.preference

import android.content.Context
import com.iamhabib.easy_preference.EasyPreference

/**
 * Created by carloscardoso on 24/09/18 at 14:04.
 */

/**
 * Helper class to store data in Preference with
 * Allow to save strings, integers and booleans value.
 * Also allow to recturn this stored data by providing it key
 *
 * @param context una instancia del contexto
 * /
 */
class Nube(internal var context: Context) {

    /**
     * Store a string value with name key
     *
     * @param key: the name of the value to be stored
     * @param value: the value to be stored
     */
    fun saveStringValue(key: String, value: String) {
        EasyPreference.with(context)
                .addString(key, value)
                .save()
    }

    /**
     * Return a string given a key
     *
     * @param key: the name of the value stored
     * @return: the value that matches the key
     */
    fun getStringValue(key: String): String? {
        try {
            return EasyPreference.with(context).getString(key, null)
        } catch (e: NullPointerException) {
            return null
        }

    }

    /**
     * Store a integer value with name key
     *
     * @param key: the name of the value to be stored
     * @param value: the value to be stored
     */
    fun saveIntegerValue(key: String, value: Int) {
        EasyPreference.with(context).addInt(key, value).save()
    }


    /**
     * Return an integer value given a key
     *
     * @param key: the name of the value to  be returned
     * @return: the value stored
     */
    fun getIntegerValue(key: String): Int? {
        try {
            return EasyPreference.with(context).getInt(key, 0)
        } catch (e: Exception) {
            return null
        }

    }


    /**
     * Store a boolean value  with a  key
     *
     * @param key: the name of the boolean value
     * @param value: the boolean value itself
     *
     */
    fun saveBooleanValue(key: String, value: Boolean) {
        EasyPreference.with(context).addBoolean(key, value).save()
    }

    /**
     * Return a boolean value given a key.
     *
     * @param key: the name of the value
     * @return: the boolean value (false if the key doesnt exists)
     *
     */
    fun getBooleanValue(key: String): Boolean? {
        return EasyPreference.with(context).getBoolean(key, false)
    }

    /**
     * Remove a value stored given a key
     *
     * @param key: the name of the value to be removed
     */
    fun removeValue(key: String) {
        EasyPreference.with(context).remove(key).save()
    }


    /**
     * Remove all values stored in all application
     * @param context an instance of Context
     */
    fun clearAll() {
        EasyPreference.with(context).clearAll().save()
    }

}
