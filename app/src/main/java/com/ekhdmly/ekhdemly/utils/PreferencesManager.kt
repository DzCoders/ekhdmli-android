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
    }

    private val preference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        preference = context.getSharedPreferences(PREFERENCE_CONFIGURATION_NAME, PRIVATE_MODE)
        @SuppressLint("CommitPrefEdits")
        editor = preference.edit()
    }

    // Used to track the App State if First Or Nor
    var isFirstTime: Boolean
        get() = !preference.getBoolean(FIRST_TIME, true)
        set(value) {
            editor.putBoolean(FIRST_TIME, false).commit()
        }

    // Used to track the User State of Login or NOt
    var isLogin: Boolean
        get() = preference.getBoolean(LOGIN, false)
        set(value) {
            editor.putBoolean(LOGIN, value).commit()
        }

    // Used to track the User ID
    var userId: Long
        get() = preference.getLong(USER_ID, -1)
        set(value) {
            editor.putLong(USER_ID, value).commit()
        }

    fun removePreference() = editor.clear().commit()
}
