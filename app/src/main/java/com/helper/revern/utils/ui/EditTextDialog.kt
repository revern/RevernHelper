package com.helper.revern.utils.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.EditText
import com.helper.revern.utils.Keyboard
import rx.functions.Func1

//TODO make normal DialogFragment
class EditTextDialog {

    companion object {
        fun show(context: Context, titleRes: Int, func1: Func1<String, Unit>) {
            val uiEditText = EditText(context)
            uiEditText.imeOptions = EditorInfo.IME_ACTION_DONE
            val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            uiEditText.layoutParams = layoutParams

            val dialog = AlertDialog.Builder(context)
                    .setTitle(context.getString(titleRes))
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, { _, _ ->
                        func1.call(uiEditText.text.toString())
                    })
                    .setView(uiEditText)
                    .create()

            dialog.window.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            dialog.show()
        }
    }

}