package com.helper.revern.tasks.rc_view

import android.view.ViewGroup
import com.helper.revern.base.rc_view.BaseDragAndDropRcvAdapter
import com.helper.revern.tasks.models.Task
import com.helper.revern.base.rc_view.OnRcvItemClickListener
import rx.functions.Func1

class TasksAdapter(items: MutableList<Task>,
                   holderCreator: Func1<ViewGroup, TaskHolder>,
                   onRcvItemClickListener: OnRcvItemClickListener<Task>?) :
        BaseDragAndDropRcvAdapter<Task, TaskHolder>(items, holderCreator, onRcvItemClickListener) {

}