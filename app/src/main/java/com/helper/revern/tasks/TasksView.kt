package com.helper.revern.tasks

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.helper.revern.base.BaseView
import com.helper.revern.tasks.models.Task

interface TasksView : BaseView {

    fun showTasks(tasks: MutableList<Task>)

    @StateStrategyType(value = SkipStrategy::class)
    fun addTask(task: Task)

    @StateStrategyType(value = SkipStrategy::class)
    fun removeTask(task: Task)

}
