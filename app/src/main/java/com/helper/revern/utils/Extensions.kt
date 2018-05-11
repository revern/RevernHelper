package com.helper.revern.utils

import android.graphics.Paint
import android.widget.TextView

fun TextView.crossText() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.uncrossText() {
    this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}
