package com.helper.revern.base

import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.support.annotation.LayoutRes
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import butterknife.ButterKnife

abstract class BaseDialogFragment : DialogFragment() {

    companion object {
        protected fun <T : BaseDialogFragment> show(dialogFragment: T,
                                                    activity: Activity): T {
            val fragmentTransaction = activity.fragmentManager.beginTransaction()
            val existingDialog = activity.fragmentManager
                    .findFragmentByTag(dialogFragment.getSimpleTag())
            if (existingDialog != null) {
                fragmentTransaction.remove(existingDialog)
                if (existingDialog is DialogFragment) {
                    existingDialog.dismissAllowingStateLoss()
                }
            }
            fragmentTransaction.addToBackStack(null)
            dialogFragment.show(fragmentTransaction, dialogFragment.getSimpleTag())
            return dialogFragment
        }
    }

    @LayoutRes abstract fun getLayoutRes(): Int

    abstract fun getSimpleTag(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            parseArguments(arguments)
        }
        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        }
    }

    protected fun restoreState(savedState: Bundle) {}

    protected fun parseArguments(args: Bundle) {}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(getLayoutRes(), container, false)
        ButterKnife.bind(view)
        return view
    }

}