package com.helper.revern.tasks

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.helper.revern.base.BasePresenter
import com.helper.revern.tasks.dao.TasksInteractor
import com.helper.revern.tasks.models.Task
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

    fun updateTasks() {
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

    fun removeTask(task: Task) {
        TasksInteractor.delete(task)
        viewState.removeTask(task)
        for (i in 0 until tasks.size) {
            if (tasks[i].position != i) {
                tasks[i].position = i
                TasksInteractor.update(tasks[i])
            }
        }
    }

    fun removeAllCrossed() {
        tasks
                .filter { it.crossed }
                .forEach { removeTask(it) }
    }
}