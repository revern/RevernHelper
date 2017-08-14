package com.helper.revern.tasks.rc_view

import android.view.ViewGroup
import com.helper.revern.tasks.models.Task
import com.helper.revern.utils.ui.rc_view.BaseRcAdapter
import com.helper.revern.utils.ui.rc_view.OnRcItemClickListener
import rx.functions.Func1

/**
 * Created by Revern on 04.08.2017.
 */
class TasksAdapter(items: MutableList<Task>,
                   func0: Func1<ViewGroup, TaskHolder>,
                   onRcItemClickListener: OnRcItemClickListener<Task>?) :
        BaseRcAdapter<Task, TaskHolder>(items, func0, onRcItemClickListener) {

}