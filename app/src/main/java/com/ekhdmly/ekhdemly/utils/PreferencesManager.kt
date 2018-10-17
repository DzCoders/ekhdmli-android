package com.ekhdmly.ekhdemly.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    companion object {
        const val TAG = "manager"
        const val PRIVATE_MODE = 0
        const val PREFERENCE_CONFIGURATION_NAME = "configuration"
        const val FIRST_TIME = "isFirstTime"
        const val LOGIN = "Login"
        const val USER_ID = "userId"
        const val HASH = "hash"
    }

    private val preference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preference = context.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE)
        @SuppressLint("CommitPrefEdits")
        editor = preference.edit()
    }

    // Used to track the App State if First Or Not.
    var isFirstTime: Boolean
        get() = !preference.getBoolean(FIRST_TIME, true)
        set(value) {
            editor.putBoolean(FIRST_TIME, false).commit()
        }

    // Used to track the User State of Login or Not.
    var isLogin: Boolean
        get() = preference.getBoolean(LOGIN, false)
        set(value) {
            editor.putBoolean(LOGIN, value).commit()
        }

    // Used to track the User ID.
    var userId: Long
        get() = preference.getLong(USER_ID, -1)
        set(value) {
            editor.putLong(USER_ID, value).commit()
        }

    // Hash of the File.
    var hash : String?
        get() = preference.getString(HASH,null)
        set(value){
            editor.putString(HASH , value)
        }

    /**
     * This Function calculate the current Hash of file.
     */
    fun calculateCurrentHash() : String{
        return ""
    }

    /**
     * This Function calculate the Hash of a given data.
     * @param data : the data array we want to hash.
     */
    fun calculateHash(data : Array<String>) : String {
        return ""
    }

    fun removePreference() = editor.clear().commit()
}
