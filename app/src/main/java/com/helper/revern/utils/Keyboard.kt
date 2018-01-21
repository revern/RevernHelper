package com.helper.revern.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.support.annotation.NonNull
import android.app.Activity



class Keyboard {

    companion object {

        fun show(view: View) {
            val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }

        fun show(context: Context) {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }

        fun hide(activity: Activity) {
            val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            val view = activity.currentFocus
            if (view != null) {
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        fun hide(view: View) {
            val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            if (!inputManager.isActive) return

            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}