package com.helper.revern.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpDelegate
import com.bluelinelabs.conductor.Controller
import com.helper.revern.utils.ui.UiInfo

abstract class BaseController : Controller {

    constructor() : this(null)
    constructor(args: Bundle?) : super(args)

    private var mvpDelegate: MvpDelegate<out BaseController>? = null

    abstract fun getUiInfo(): UiInfo
    abstract fun onCreateView(view: View)

    private fun getMvpDelegate(): MvpDelegate<out BaseController> {
        if (mvpDelegate == null) {
            mvpDelegate = MvpDelegate(this)
        }
        return mvpDelegate as MvpDelegate<out BaseController>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(getUiInfo().resLayout, container, false)
        ButterKnife.bind(this, view)

        onCreateView(view)

        getMvpDelegate().onCreate()
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        getMvpDelegate().onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        getMvpDelegate().onDetach()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        getMvpDelegate().onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getMvpDelegate().onSaveInstanceState(outState)
        getMvpDelegate().onDetach()
    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        getMvpDelegate().onCreate(savedViewState)
    }

    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate().onDestroy()
    }

}