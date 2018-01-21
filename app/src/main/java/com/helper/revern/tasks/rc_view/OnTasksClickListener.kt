package com.helper.revern.tasks.rc_view

import android.view.View
import android.widget.TextView
import com.helper.revern.base.rc_view.OnRcvItemClickListener
import com.helper.revern.tasks.models.Task
import com.helper.revern.utils.EditTexts

class OnTasksClickListener : OnRcvItemClickListener<Task> {

    override fun onItemClick(view: View, item: Task) {
        if (view is TextView) {
            if (item.crossed) {
                EditTexts.uncrossTextView(view)
                item.crossed = false
            } else {
                EditTexts.crossTextView(view)
                item.crossed = true
            }
        }
    }

}