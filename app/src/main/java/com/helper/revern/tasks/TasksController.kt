package com.helper.revern.tasks

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.base.rc_view.OnRcvItemClickListener
import com.helper.revern.tasks.models.Task
import com.helper.revern.tasks.rc_view.TaskHolder
import com.helper.revern.tasks.rc_view.TasksAdapter
import com.helper.revern.tasks.rc_view.TasksDragAndDropHelperCallback
import com.helper.revern.utils.EditTexts
import com.helper.revern.utils.Strings
import com.helper.revern.utils.ui.EditTextDialog
import com.helper.revern.utils.ui.UiInfo
import rx.functions.Func0
import rx.functions.Func1


class TasksController : BaseController(), TasksView, OnRcvItemClickListener<Task> {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: TasksPresenter

    @BindView(R.id.tasks_rv)
    lateinit var uiTasks: RecyclerView
    @BindView(R.id.add_task_fab)
    lateinit var uiAddTask: FloatingActionButton

    private lateinit var adapter: TasksAdapter

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_tasks, R.string.title_tasks)
    }

    override fun onCreateView(view: View) {
        uiTasks.layoutManager = LinearLayoutManager(applicationContext)
        uiAddTask.setOnClickListener { _ ->
            EditTextDialog.show(activity!!, R.string.title_new_task,
                    Func1 { text -> if (!Strings.isEmty(text)) presenter.addTask(text) })
        }
    }

    override fun showTasks(tasks: MutableList<Task>) {
        adapter = TasksAdapter(tasks, TaskHolder.getHolderCreator(), this)
        uiTasks.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(TasksDragAndDropHelperCallback(adapter, Func0 {
            presenter.updateTasks()
        }))
        itemTouchHelper.attachToRecyclerView(uiTasks)
    }

    fun removeAllCrossed() {
        presenter.removeAllCrossed()
    }

    override fun onItemClick(view: View, item: Task) {
        if (view is TextView) {
            if (item.crossed) {
                EditTexts.uncrossTextView(view)
            } else {
                EditTexts.crossTextView(view)
            }
            presenter.changeTaskCrossing(item)
        }
    }

    override fun addTask(task: Task) {
        adapter.add(task)
        uiTasks.scrollToPosition(adapter.itemCount - 1)
    }

    override fun removeTask(task: Task) {
        adapter.remove(task)
    }

}