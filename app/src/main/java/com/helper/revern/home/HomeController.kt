package com.helper.revern.home

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.MvpDelegate
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.R
import com.helper.revern.utils.ui.BaseController
import com.helper.revern.utils.ui.UiInfo

/**
 * Created by Revern on 31.07.2017.
 */

class HomeController : BaseController<HomeView, HomePresenter, HomeController>(), HomeView {

    @InjectPresenter(type = PresenterType.LOCAL) lateinit var presenter: HomePresenter

    override fun createUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_home)
    }

    override fun changeTitle(msg: String) {
        uiTitle.text = msg
    }

    override fun createMvpDelegate(): MvpDelegate<HomeController> {
        return MvpDelegate(this)
    }

    @BindView(R.id.title_home) lateinit var uiTitle: TextView
    @BindView(R.id.tab_layout) lateinit var uiTabs: TabLayout
    @BindView(R.id.view_pager) lateinit var uiPager: ViewPager

    @OnClick(R.id.title_home) fun onTitleClick(view: View) {
        presenter.changeTitle()
    }
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
//        var view = inflater.inflate(R.layout.screen_home, container, false)
//        ButterKnife.bind(this, view)
//
//        initPager()
//        return view
//    }

    override fun onAttach(view: View) {
        initPager()
        super.onAttach(view)
    }

    fun initPager() {
        val adapter = ConductorAdapter(this)
        uiPager.adapter = adapter

        uiTabs.setupWithViewPager(uiPager)
    }

}