package com.helper.revern.utils.ui.dialogs

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import butterknife.OnEditorAction
import com.helper.revern.R
import com.helper.revern.base.BaseDialogFragment
import com.helper.revern.utils.Keyboard
import com.helper.revern.utils.Strings
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

    @BindView(R.id.title) lateinit var uiTitle: TextView
    @BindView(R.id.edit_text) lateinit var uiEditText: EditText

    @OnClick(R.id.positive_btn) fun onPositiveClick() {
        val text = uiEditText.text.toString()
        if (Strings.isEmty(text)) {
            uiEditText.error = getString(R.string.error_empty_text)
        } else {
            onPositiveClickFunc.call(uiEditText.text.toString())
            dismiss()
        }
    }

    @OnClick(R.id.negative_btn) fun onNegativeClick() = dismiss()

    @OnEditorAction(R.id.edit_text) fun onEnterClick(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onPositiveClick()
            return true
        }
        return false
    }

    override fun getLayoutRes(): Int = R.layout.dialog_edit_text

    override fun getSimpleTag(): String = TAG

    override fun parseArguments(args: Bundle) {}

    override fun restoreState(savedState: Bundle) {}

    override fun onPostCreateView() {
        uiTitle.text = title
        Keyboard.show(activity)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        Keyboard.hide(uiEditText)
        super.onDismiss(dialog)
    }

}