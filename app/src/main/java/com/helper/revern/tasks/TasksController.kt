package com.helper.revern.tasks

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.base.rc_view.OnRcvItemClickListener
import com.helper.revern.tasks.models.Task
import com.helper.revern.tasks.rc_view.TaskHolder
import com.helper.revern.tasks.rc_view.TasksAdapter
import com.helper.revern.tasks.rc_view.TasksDragAndDropHelperCallback
import com.helper.revern.utils.Strings
import com.helper.revern.utils.crossText
import com.helper.revern.utils.ui.UiInfo
import com.helper.revern.utils.ui.dialogs.EditTextDialog
import com.helper.revern.utils.uncrossText
import kotlinx.android.synthetic.main.screen_tasks.view.*
import rx.functions.Func0
import rx.functions.Func1

class TasksController : BaseController, TasksView, OnRcvItemClickListener<Task> {

    companion object {
        const val ARG_TYPE = "argType"
    }

    private lateinit var type: String

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: TasksPresenter

    private lateinit var adapter: TasksAdapter

    constructor(type: String) {
        this.type = type
    }

    constructor(args: Bundle?) : super(args)

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_tasks, type)
    }

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providePresenterTag(): TasksPresenter {
        val tasksPresenter = TasksPresenter()
        tasksPresenter.setType(type)
        return tasksPresenter
    }

    override fun onCreateView(view: View) {
        view.tv_tasks.layoutManager = LinearLayoutManager(applicationContext)
        view.fab_add_task.setOnClickListener {
            activity?.let {
                EditTextDialog.show(it, R.string.title_add_task,
                        Func1 { text -> if (!Strings.isEmty(text)) presenter.addTask(text) })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_TYPE, type)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        type = savedInstanceState.getString(ARG_TYPE)
    }

    override fun showTasks(tasks: MutableList<Task>) {
        adapter = TasksAdapter(tasks, TaskHolder.getHolderCreator(), this)
        view?.let {
            it.tv_tasks.adapter = adapter
            val itemTouchHelper = ItemTouchHelper(TasksDragAndDropHelperCallback(adapter, Func0 {
                presenter.updateTasks()
            }))
            itemTouchHelper.attachToRecyclerView(it.tv_tasks)
        }
    }

    fun removeAllCrossed() {
        presenter.removeAllCrossed()
    }

    override fun onItemClick(view: View, item: Task) {
        if (view is TextView) {
            if (item.crossed) {
                view.uncrossText()
            } else {
                view.crossText()
            }
            presenter.changeTaskCrossing(item)
        }
    }

    override fun addTask(task: Task) {
        adapter.add(task)
        view?.let {
            it.tv_tasks.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun removeTask(task: Task) {
        adapter.remove(task)
    }

}
