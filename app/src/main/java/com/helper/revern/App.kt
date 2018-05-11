package com.helper.revern

import android.app.Application
import android.content.Context
import com.helper.revern.utils.Prefs

class App : Application() {

    companion object {
        private lateinit var context: Context

        fun context(): Context = context
    }

    override fun onCreate() {
        super.onCreate()

        context = this
        Prefs.initialize(this)
    }

}

