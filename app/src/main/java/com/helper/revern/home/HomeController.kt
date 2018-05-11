package com.helper.revern.home

import android.app.AlertDialog
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.App
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.tasks.TasksController
import com.helper.revern.utils.Strings
import com.helper.revern.utils.ui.UiInfo
import com.helper.revern.utils.ui.dialogs.EditTextDialog
import kotlinx.android.synthetic.main.screen_home.view.*
import rx.functions.Func1

class HomeController : BaseController(), HomeView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: HomePresenter

    init {
        setHasOptionsMenu(true)
    }

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_home, App.context().getString(R.string.app_name))
    }

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(view: View) {
    }

    override fun initPager(pages: ArrayList<BaseController>) {
        adapter = HomeAdapter(this, pages)

        view?.let {
            it.view_pager.offscreenPageLimit = 30
            it.view_pager.adapter = adapter
        }

        initTabs()
    }

    private fun initTabs() {
        view?.let {
            it.tabs.setupWithViewPager(it.view_pager)
            val tab = it.tabs.newTab()
            val imgBtn = ImageButton(activity)
            activity?.let {
                imgBtn.setBackgroundColor(ContextCompat.getColor(it, android.R.color.transparent)) //TODO remake with saving button effects
            }
            imgBtn.setImageResource(R.drawable.ic_add_box_accent_36dp)
            imgBtn.setOnClickListener {
                activity?.let {
                    EditTextDialog.show(it, R.string.dialog_title_add_list, Func1 { text ->
                        if (!Strings.isEmty(text)) presenter.addList(text)
                    })
                }
            }
            tab.customView = imgBtn
            it.tabs.addTab(tab, adapter.count, false)
        }
    }

    override fun selectPage(position: Int) {
        view?.let {
            it.view_pager.currentItem = position
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                view?.let {
                    val controller = adapter.getPageAt(it.view_pager.currentItem)
                    if (controller is TasksController) {
                        controller.removeAllCrossed()
                    }
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
        adapter.addPage(page)
        initTabs()
    }

    override fun showError(errorText: String) {
        AlertDialog.Builder(activity)
                .setTitle(errorText)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show()
    }

    override fun showError(errorTextRes: Int) {
        activity?.let {
            showError(it.getString(errorTextRes))
        }
    }

}
