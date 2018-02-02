package com.helper.revern.tasks.dialogs

import android.app.Activity
import android.os.Bundle
import com.helper.revern.R
import com.helper.revern.base.BaseDialogFragment
import rx.functions.Func1

class AddTaskDialog : BaseDialogFragment() {

    companion object {
        const val TAG = "AddTaskDialog"

        fun show(activity: Activity, listName: String/*, func: Func1<String, Unit>*/) {
            val dialog = AddTaskDialog()
            dialog.show(activity.fragmentManager, TAG)
        }
    }

    override fun getLayoutRes(): Int = R.layout.dialog_add_task

    override fun getSimpleTag(): String = TAG

    override fun parseArguments(args: Bundle) {}

    override fun restoreState(savedState: Bundle) {}
}