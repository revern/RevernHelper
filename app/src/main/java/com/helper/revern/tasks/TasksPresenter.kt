package com.helper.revern.tasks

import com.arellomobile.mvp.InjectViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.helper.revern.base.BasePresenter
import com.helper.revern.tasks.models.Task
import com.helper.revern.utils.Prefs
import java.util.*

@InjectViewState
class TasksPresenter : BasePresenter<TasksView>() {

    companion object {
        private const val KEY_TASKS = "tasks"
    }

    private var tasks = ArrayList<Task>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSavedTasks()
    }

    private fun loadSavedTasks() {
        val savedTasksJson: String? = Prefs.prefs.getString(KEY_TASKS, null)
        if (savedTasksJson != null) {
            val savedTasks: List<Task> = Gson().fromJson(savedTasksJson, object : TypeToken<List<Task>>() {}.type)
            tasks.clear()
            tasks.addAll(savedTasks)
        }

        viewState.showTasks(tasks)
    }

    fun saveTasks() {
        val tasksJson = Gson().toJson(tasks)
        Prefs.prefs.edit().putString(KEY_TASKS, tasksJson).apply()
    }

    fun addTask(taskName: String) {
        val task = Task(taskName)
        viewState.addTask(task)
        saveTasks()
    }
}