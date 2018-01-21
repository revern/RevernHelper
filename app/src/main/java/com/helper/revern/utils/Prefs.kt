package com.helper.revern.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs private constructor() {

    companion object {
        lateinit var prefs : SharedPreferences

        fun initialize(context: Context) {
            prefs = context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
        }
    }

}