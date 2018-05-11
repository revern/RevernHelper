package com.helper.revern.utils.ui.dialogs

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.helper.revern.R
import com.helper.revern.base.BaseDialogFragment
import com.helper.revern.utils.Keyboard
import com.helper.revern.utils.Strings
import kotlinx.android.synthetic.main.dialog_edit_text.view.*
import rx.functions.Func1

class EditTextDialog : BaseDialogFragment() {

    companion object {
        const val TAG = "EditTextDialog"

        fun show(activity: Activity, titleRes: Int, onPositiveClickFunc: Func1<String, Unit>) {
            val dialog = EditTextDialog()
            dialog.title = activity.getString(titleRes)
            dialog.onPositiveClickFunc = onPositiveClickFunc
            dialog.show(activity.fragmentManager, TAG)
        }
    }

    private lateinit var title: String
    private lateinit var onPositiveClickFunc: Func1<String, Unit>

    override fun getLayoutRes(): Int = R.layout.dialog_edit_text

    override fun getSimpleTag(): String = TAG

    override fun parseArguments(args: Bundle) {}

    override fun restoreState(savedState: Bundle) {}

    override fun onPostCreateView(view: View) {
        view.tv_title.text = title

        view.btn_positive.setOnClickListener { onPositiveClick() }
        view.btn_negative.setOnClickListener { onNegativeClick() }

        view.edit_text.setOnEditorActionListener { _, actionId, _ -> onEnterClick(actionId) }

        Keyboard.show(activity) //TODO replace to edit_text
    }

    override fun onDismiss(dialog: DialogInterface?) {
        Keyboard.hide(view.edit_text)
        super.onDismiss(dialog)
    }

    private fun onPositiveClick() {
        val text = view.edit_text.text.toString()
        if (Strings.isEmty(text)) {
            view.edit_text.error = getString(R.string.error_empty_text)
        } else {
            onPositiveClickFunc.call(view.edit_text.text.toString())
            dismiss()
        }
    }

    private fun onNegativeClick() = dismiss()

    private fun onEnterClick(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onPositiveClick()
            return true
        }
        return false
    }

}
