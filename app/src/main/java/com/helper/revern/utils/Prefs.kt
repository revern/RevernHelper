package com.helper.revern.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs private constructor() {

    companion object {
        private lateinit var sPrefs: SharedPreferences

        fun initialize(context: Context) {
            sPrefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        }

        fun instance() = sPrefs
    }

}