package com.helper.revern.home

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.utils.ui.UiInfo

class HomeController : BaseController(), HomeView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: HomePresenter

    init {
        setHasOptionsMenu(true)
    }

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_home, R.string.app_name)
    }

    override fun changeTitle(msg: String) {
        uiTitle.text = msg
    }

    @BindView(R.id.title_home)
    lateinit var uiTitle: TextView
    @BindView(R.id.tab_layout)
    lateinit var uiTabs: TabLayout
    @BindView(R.id.view_pager)
    lateinit var uiPager: ViewPager

    @OnClick(R.id.title_home)
    fun onTitleClick(view: View) {
        presenter.changeTitle("click")
    }

    override fun onCreateView(view: View) {
        initPager()
    }

    private fun initPager() {
        val adapter = HomeAdapter(this, applicationContext)
        uiPager.adapter = adapter

        uiTabs.setupWithViewPager(uiPager)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                return true
            }
            R.id.menu_settings -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}