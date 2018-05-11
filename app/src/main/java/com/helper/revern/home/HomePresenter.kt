package com.helper.revern.home

import com.arellomobile.mvp.InjectViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.base.BasePresenter
import com.helper.revern.tasks.TasksController
import com.helper.revern.utils.Prefs

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
        viewState.initPager(pages)
    }

    fun addList(listName: String) {
        when {
            listsNames.size > 15 -> viewState.showError(R.string.error_too_much_lists)
            listsNames.contains(listName) -> viewState.showError(R.string.error_list_already_exists)
            else -> {
                listsNames.add(listName)
                saveLists()
                viewState.addPage(TasksController(listName))
                viewState.selectPage(listsNames.size - 1)
            }
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
