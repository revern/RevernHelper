package com.helper.revern.tasks.dao

import com.helper.revern.AppDatabase
import com.helper.revern.tasks.models.Task

class TasksInteractor {

    companion object {
        private fun dao() = AppDatabase.instance().taskDao()

        fun getAllTasks() = dao().getAllTasks()

        fun getWithType(type: String) = dao().getTasksByType(type)

        fun getWithTypeSorted(type: String) = dao().getAllTasksByTypeSorted(type)

        fun add(task: Task) = dao().insertTask(task)

        fun addAll(tasks: List<Task>) = dao().insertAll(tasks)

        fun update(task: Task) = dao().updateTask(task)

        fun updateAll(tasks: List<Task>) = dao().updateAllTask(tasks)

        fun delete(task: Task) = dao().delete(task)

        fun deleteAll(tasks: List<Task>) {
            for (task in tasks) {
                delete(task)
            }
        }
    }

}