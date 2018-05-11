package com.helper.revern.tasks.rc_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.helper.revern.R
import com.helper.revern.base.rc_view.BaseRcvHolder
import com.helper.revern.tasks.models.Task
import com.helper.revern.utils.crossText
import com.helper.revern.utils.uncrossText
import kotlinx.android.synthetic.main.item_task.view.*
import rx.functions.Func1

class TaskHolder(itemView: View) : BaseRcvHolder<Task>(itemView) {

    companion object {
        fun getHolderCreator(): Func1<ViewGroup, TaskHolder> =
                Func1 { viewGroup ->
                    TaskHolder(LayoutInflater.from(viewGroup.context)
                            .inflate(R.layout.item_task, viewGroup, false))
                }
    }

    override fun bind(item: Task) {
        itemView.task_name.text = item.name
        if (item.crossed) {
            itemView.task_name.crossText()
        } else {
            itemView.task_name.uncrossText()
        }
    }

}
