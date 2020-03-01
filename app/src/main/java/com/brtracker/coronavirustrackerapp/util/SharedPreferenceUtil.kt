package com.brtracker.coronavirustrackerapp.util

import android.content.Context

class SharedPreferenceUtil {

    companion object {


        fun saveInitialDataDownloaded(context: Context, boolean: Boolean) {
            val sharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            sharedPreferences.edit().putBoolean(IS_INITIAL_DATA_COMPLETED, boolean).apply()
        }

        fun getIsInitialDataDownloaded(context: Context): Boolean {
            val sharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(IS_INITIAL_DATA_COMPLETED, false)
        }

    }
}