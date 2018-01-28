package com.helper.revern.tasks

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.helper.revern.AppDatabase
import com.helper.revern.base.BasePresenter
import com.helper.revern.tasks.dao.TasksInteractor
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
//        val savedTasksJson: String? = Prefs.instance().getString(KEY_TASKS, null)
//        if (savedTasksJson != null) {
//            val savedTasks: List<Task> = Gson().fromJson(savedTasksJson, object : TypeToken<List<Task>>() {}.type)
//            tasks.clear()
//            tasks.addAll(savedTasks)
//        }

        tasks.clear()
        tasks.addAll(TasksInteractor.getWithTypeSorted("task"))

        viewState.showTasks(tasks)
    }

    fun saveTasks() {
        Log.d("qqqwwweee", "save")
        TasksInteractor.updateAll(tasks)
//        val tasksJson = Gson().toJson(tasks)
//        Prefs.instance().edit().putString(KEY_TASKS, tasksJson).apply()
    }

    fun addTask(taskName: String) {
        val task = Task("task", taskName, tasks.size)
        viewState.addTask(task)
        TasksInteractor.add(task)
    }

    fun changeTaskCrossing(task: Task) {
        task.crossed = !task.crossed
        TasksInteractor.update(task)
    }

    fun removeLast() : Boolean {
        TasksInteractor.delete(tasks.last())
        return true
    }
}