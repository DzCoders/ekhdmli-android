package com.ekhdmly.ekhdemly.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject
import java.security.MessageDigest

class PreferencesManager(context: Context) {

    companion object {
        const val TAG = "manager"
        const val PRIVATE_MODE = 0
        const val PREFERENCE_CONFIGURATION_NAME = "configuration"
        const val FIRST_TIME = "isFirstTime"
        const val LOGIN = "Login"
        const val USER_ID = "userId"
        const val HASH = "hash"
        const val NAME = "full_name"
        const val PICTURE = "pic_path"

        /**
         * Function that returns the hash of a given String.
         * @param data : the data you want to hash.
         * @return the hash of the given data.
         */
        fun calculateHash(data: String): String {
            val bytes = data.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("") { str, it -> str + "%02x".format(it) }
        }
    }

    private val preference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preference = context.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE)
        @SuppressLint("CommitPrefEdits")
        editor = preference.edit()
    }

    // Used to track the App State if First Or Not.
    private var isFirstTime: Boolean
        get() = !preference.getBoolean(FIRST_TIME, true)
        set(value) {
            editor.putBoolean(FIRST_TIME, false).commit()
        }

    // Used to track the User State of Login or Not.
    private var isLogin: Boolean
        get() = preference.getBoolean(LOGIN, false)
        set(value) {
            editor.putBoolean(LOGIN, value).commit()
        }

    // Used to track the User ID.
    private var userId: Long
        get() = preference.getLong(USER_ID, -1)
        set(value) {
            editor.putLong(USER_ID, value).commit()
        }

    // Hash of the File.
    private var hash: String?
        get() = preference.getString(HASH, calculateHash(this.toString()))
        set(value) {
            editor.putString(HASH, value).commit()
        }
    // Used to track the user picture path
    private var picture: String?
        get() = preference.getString(PICTURE, null)
        set(value) {
            editor.putString(PICTURE, value).commit()
        }
    // Used to track the full name
    private var fullName: String?
        get() = preference.getString(NAME, null)
        set(value) {
            editor.putString(NAME, value).commit()

        }

    /**
     * This Function calculate the current Hash of file.
     */
    fun calculateCurrentHash(): String {
        return calculateHash(toString())
    }

    /**
     * Function to remove the data in the preference file.
     * @return boolean indicate the operation is done correctly or not.
     */
    fun removePreference() = editor.clear().commit()

    override fun toString(): String {
        val `object` = JSONObject()
        `object`.put(USER_ID, userId)
        `object`.put(LOGIN, isLogin)
        `object`.put(FIRST_TIME, isFirstTime)
        `object`.put(PICTURE, picture)
        `object`.put(NAME, fullName)
        return `object`.toString()
    }
}
