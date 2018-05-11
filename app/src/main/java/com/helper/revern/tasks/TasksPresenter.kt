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
    private lateinit var type: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSavedTasks()
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getType(): String = type

    private fun loadSavedTasks() {
        val savedTasksJson: String? = Prefs.instance().getString(type, null)
        if (savedTasksJson != null) {
            val savedTasks: List<Task> = Gson().fromJson(savedTasksJson, object : TypeToken<List<Task>>() {}.type)
            tasks.clear()
            tasks.addAll(savedTasks)
        }

        viewState.showTasks(tasks)
    }

    fun updateTasks() {
        val tasksJson = Gson().toJson(tasks)
        Prefs.instance().edit().putString(type, tasksJson).apply()
    }

    fun addTask(taskName: String) {
        val task = Task("task", taskName)
        viewState.addTask(task)
        updateTasks()
    }

    fun changeTaskCrossing(task: Task) {
        task.crossed = !task.crossed
        updateTasks()
    }

    fun removeAllCrossed() {
        tasks
                .filter { it.crossed }
                .forEach { viewState.removeTask(it) }
        updateTasks()
    }

}
