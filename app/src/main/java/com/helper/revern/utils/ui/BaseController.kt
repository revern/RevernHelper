package com.helper.revern.utils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpDelegate
import com.bluelinelabs.conductor.Controller

/**
 * Created by Revern on 01.08.2017.
 */


abstract class BaseController<V : BaseView, P : BasePresenter<V>, C : BaseController<V, P, C>> : Controller {

    constructor() : this(null)

    constructor(args: Bundle?) : super(args)

    abstract fun createUiInfo(): UiInfo

    private lateinit var uiInfo: UiInfo
    private var mvpDelegate: MvpDelegate<C>? = null

    //TODO try on JAVA. crash when in base class, works only when init on child class
//    @InjectPresenter(type = PresenterType.LOCAL) lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        uiInfo = createUiInfo()
        var view = inflater.inflate(uiInfo.resLayout, container, false)
        ButterKnife.bind(this, view)

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
        getMvpDelegate().onCreate()
    }

    fun getMvpDelegate(): MvpDelegate<C> {
        if (mvpDelegate == null) {
            mvpDelegate = createMvpDelegate()
        }
        return mvpDelegate as MvpDelegate<C>
    }

    abstract fun createMvpDelegate(): MvpDelegate<C>

    override fun onDestroy() {
        super.onDestroy()

        if (activity?.isFinishing ?: false) {
//            getMvpDelegate().onDestroy()
            return
        }

    }


}