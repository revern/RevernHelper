package com.helper.revern.utils

import android.graphics.Paint
import android.widget.TextView

class EditTexts {
    companion object {
        fun crossTextView(view: TextView) {
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }

        fun uncrossTextView(view: TextView) {
            view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

    }
}