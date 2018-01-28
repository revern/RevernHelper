package com.helper.revern.tasks.rc_view

import android.view.ViewGroup
import com.helper.revern.base.rc_view.BaseDragAndDropRcvAdapter
import com.helper.revern.tasks.models.Task
import com.helper.revern.base.rc_view.OnRcvItemClickListener
import rx.functions.Func1
import java.util.*

class TasksAdapter(items: MutableList<Task>,
                   holderCreator: Func1<ViewGroup, TaskHolder>,
                   onRcvItemClickListener: OnRcvItemClickListener<Task>?) :
        BaseDragAndDropRcvAdapter<Task, TaskHolder>(items, holderCreator, onRcvItemClickListener) {

    override fun onItemMove(positionFrom: Int, positionTo: Int) {
        if (positionFrom < positionTo) {
            for (i in positionFrom until positionTo) {
                swapItems(i, i + 1)
            }
        } else {
            for (i in positionFrom downTo positionTo + 1) {
                swapItems(i, i - 1)
            }
        }
        notifyItemMoved(positionFrom, positionTo)
    }

    private fun swapItems(positionFrom: Int, positionTo: Int) {
        getItems()[positionFrom].position = positionTo
        getItems()[positionTo].position = positionFrom
        Collections.swap(getItems(), positionFrom, positionTo)
    }

}