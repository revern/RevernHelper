package com.helper.revern.home

import android.app.AlertDialog
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.App
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.tasks.TasksController
import com.helper.revern.utils.ui.UiInfo

class HomeController : BaseController(), HomeView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: HomePresenter

    init {
        setHasOptionsMenu(true)
    }

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_home, App.context().getString(R.string.app_name))
    }

    @BindView(R.id.tab_layout)
    lateinit var uiTabs: TabLayout
    @BindView(R.id.view_pager)
    lateinit var uiPager: ViewPager

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(view: View) {
    }

    override fun initPager(pages: ArrayList<BaseController>) {
        initPager(pages, 0)
    }

    override fun initPager(pages: ArrayList<BaseController>, position: Int) {
        adapter = HomeAdapter(this, pages)
        uiPager.offscreenPageLimit = 30
        uiPager.adapter = adapter

        uiTabs.setupWithViewPager(uiPager)
        uiPager.currentItem = position
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                val controller = adapter.getPageAt(uiPager.currentItem)
                if (controller is TasksController) {
                    controller.removeAllCrossed()
                }
                return true
            }
            R.id.menu_settings -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addPage(page: BaseController) {
        val position = adapter.addPage(page)
        uiPager.currentItem = position
    }

    override fun showError(errorText: String) {
        AlertDialog.Builder(activity)
                .setTitle(errorText)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show()
    }

    override fun showError(errorTextRes: Int) {
        showError(activity!!.getString(errorTextRes))
    }

}