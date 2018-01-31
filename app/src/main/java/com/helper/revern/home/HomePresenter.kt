package com.helper.revern.home

import com.arellomobile.mvp.InjectViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.base.BasePresenter
import com.helper.revern.new_list.NewListController
import com.helper.revern.new_list.events.AddNewListEvent
import com.helper.revern.tasks.TasksController
import com.helper.revern.utils.Prefs
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class HomePresenter : BasePresenter<HomeView>() {

    companion object {
        const val KEY_ALL_LISTS_NAMES = "keyAllListsNames"
    }

    private val listsNames = ArrayList<String>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadLists()

        val pages = ArrayList<BaseController>()
        listsNames.mapTo(pages) { TasksController(it) }
        pages.add(NewListController())
        viewState.initPager(pages)
    }

    override fun attachView(view: HomeView?) {
        super.attachView(view)
        EventBus.getDefault().register(this)
    }

    override fun detachView(view: HomeView?) {
        super.detachView(view)
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun addList(event: AddNewListEvent) {
        if (!listsNames.contains(event.listName)) {
            listsNames.add(event.listName)
            saveLists()
//            viewState.addPage(TasksController(event.listName))
            val pages = ArrayList<BaseController>()
            listsNames.mapTo(pages) { TasksController(it) }
            pages.add(NewListController())
            viewState.initPager(pages, pages.size - 2) //TODO remake this shit
        } else {
            viewState.showError(R.string.error_list_already_exists)
        }
    }

    private fun loadLists() {
        listsNames.clear()
        val listsNamesJson = Prefs.instance().getString(KEY_ALL_LISTS_NAMES, null)
        if (listsNamesJson != null) {
            listsNames.addAll(Gson().fromJson(listsNamesJson, object : TypeToken<List<String>>() {}.type))
        }
    }

    private fun saveLists() {
        val json = Gson().toJson(listsNames)
        Prefs.instance().edit().putString(KEY_ALL_LISTS_NAMES, json).apply()
    }


}