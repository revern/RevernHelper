package com.helper.revern

import android.app.Application
import com.helper.revern.utils.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Prefs.initialize(this)
    }

}
