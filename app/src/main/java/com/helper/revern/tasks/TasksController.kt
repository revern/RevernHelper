package com.helper.revern.tasks

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.tasks.models.Task
import com.helper.revern.tasks.rc_view.OnTasksClickListener
import com.helper.revern.tasks.rc_view.TaskHolder
import com.helper.revern.tasks.rc_view.TasksAdapter
import com.helper.revern.tasks.rc_view.TasksDragAndDropHelperCallback
import com.helper.revern.utils.Strings
import com.helper.revern.utils.ui.EditTextDialog
import com.helper.revern.utils.ui.UiInfo
import rx.functions.Func0
import rx.functions.Func1


class TasksController : BaseController(), TasksView {

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
        uiAddTask.setOnLongClickListener({ _ ->
            presenter.removeLast()
        })
    }

    override fun showTasks(tasks: MutableList<Task>) {
        adapter = TasksAdapter(tasks, TaskHolder.getHolderCreator(), OnTasksClickListener())
        uiTasks.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(TasksDragAndDropHelperCallback(adapter, Func0 {
            presenter.saveTasks()
        }))
        itemTouchHelper.attachToRecyclerView(uiTasks)
    }

    override fun addTask(task: Task) {
        adapter.add(task)
        uiTasks.scrollToPosition(adapter.itemCount - 1)
    }

    override fun deleteTask(task: Task) {
        adapter.remove(task)
    }

}