package com.helper.revern.tasks.rc_view

import android.support.v7.widget.RecyclerView
import com.helper.revern.base.rc_view.DragAndDropHelperCallback
import rx.functions.Func0

class TasksDragAndDropHelperCallback(adapter: TasksAdapter, private val func0: Func0<Unit>)
    : DragAndDropHelperCallback(adapter) {

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        val result = super.onMove(recyclerView, viewHolder, target)
        func0.call()
        return result
    }

}