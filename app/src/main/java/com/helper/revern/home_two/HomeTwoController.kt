package com.helper.revern.home_two

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpDelegate
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.bluelinelabs.conductor.Controller
import com.helper.revern.R

/**
 * Created by Revern on 31.07.2017.
 */

class HomeTwoController : Controller, HomeTwoView {
    override fun showMessage(msg: String) {
        uiHello.text = msg
    }

    companion object {
        fun makeBundle(title: String, color: Int): Bundle {
            var bundle = Bundle()
            bundle.putString("title", title)
            bundle.putInt("color", color)
            return bundle
        }
    }

    private var mvpDelegate: MvpDelegate<HomeTwoController>? = null

    @InjectPresenter(type = PresenterType.LOCAL) lateinit var presenter: HomeTwoPresenter

    constructor(bundle: Bundle) : super(bundle)

    constructor(title: String, color: Int) : this(makeBundle(title, color))

    @BindView(R.id.hello_view) lateinit var uiHello: TextView

    @OnClick(R.id.hello_view) fun onHelloClick(view: View) {
        presenter.showMessage()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        var view = inflater.inflate(R.layout.screen_two_home, container, false)
        ButterKnife.bind(this, view)

        uiHello.text = args.getString("title", "")
        uiHello.setBackgroundColor(view.context.resources.getColor(args.getInt("color", android.R.color.darker_gray)))

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

    fun getMvpDelegate(): MvpDelegate<HomeTwoController> {
        if (mvpDelegate == null) {
            mvpDelegate = MvpDelegate(this)
        }
        return mvpDelegate as MvpDelegate<HomeTwoController>
    }

    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate().onDestroy()
    }
}