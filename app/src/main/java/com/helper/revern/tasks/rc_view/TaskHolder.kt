package com.helper.revern.tasks.rc_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.helper.revern.R
import com.helper.revern.tasks.models.Task
import com.helper.revern.base.rc_view.BaseRcvHolder
import com.helper.revern.utils.EditTexts
import rx.functions.Func1

class TaskHolder(itemView: View) : BaseRcvHolder<Task>(itemView) {

    companion object {
        fun getHolderCreator(): Func1<ViewGroup, TaskHolder> =
                Func1 { viewGroup ->
                    TaskHolder(LayoutInflater.from(viewGroup.context)
                            .inflate(R.layout.item_task, viewGroup, false))
                }
    }

    @BindView(R.id.task_name) lateinit var uiName: TextView

    override fun bind(item: Task) {
        uiName.text = item.name
        if (item.crossed) {
            EditTexts.crossTextView(uiName)
        }
    }


}