package com.exxceliqsolutiions.taskuserdetails.db

import android.content.Context
import android.content.SharedPreferences
import com.exxceliqsolutiions.taskuserdetails.R

/*
*  This object contains Sheared pref variables and their functions.
* */


object SessionManager{


    const val USER_LOGIN = "user_login"


    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun saveUserLoginStatus(context: Context, status: Boolean) {
        saveBoolean(context, USER_LOGIN, status)
    }

    fun getUserLoginStatus(context: Context): Boolean? {
        return getBoolean(context , USER_LOGIN)
    }

    fun saveBoolean(context: Context, key: String, value: Boolean) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()

    }

    fun getBoolean(context: Context, key: String): Boolean? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getBoolean(USER_LOGIN , false)
    }

}