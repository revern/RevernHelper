package com.helper.revern.tasks.rc_view

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.helper.revern.R
import com.helper.revern.tasks.models.Task
import com.helper.revern.utils.ui.rc_view.BaseRcHolder

/**
 * Created by Revern on 04.08.2017.
 */
class TaskHolder(itemView: View) : BaseRcHolder<Task>(itemView) {

    @BindView(R.id.task_name) lateinit var uiName : TextView

    override fun bind(item: Task) {
        uiName.text = item.name
    }

}