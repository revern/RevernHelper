package com.helper.revern.base.rc_view

import android.view.ViewGroup
import rx.functions.Func1
import java.util.Collections

open class BaseDragAndDropRcvAdapter<T, VH : BaseRcvHolder<T>>(
        items: MutableList<T>,
        holderCreator: Func1<ViewGroup, VH>,
        onRcvItemClickListener: OnRcvItemClickListener<T>? = null)
    : BaseRcvAdapter<T, VH>(items, holderCreator, onRcvItemClickListener), DragAndDropAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(getItems(), i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(getItems(), i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

}