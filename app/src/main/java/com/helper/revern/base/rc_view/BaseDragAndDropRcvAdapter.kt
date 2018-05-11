package com.helper.revern.base.rc_view

import android.view.ViewGroup
import rx.functions.Func1
import java.util.*

open class BaseDragAndDropRcvAdapter<T, VH : BaseRcvHolder<T>>(
        items: MutableList<T>,
        holderCreator: Func1<ViewGroup, VH>,
        onRcvItemClickListener: OnRcvItemClickListener<T>? = null)
    : BaseRcvAdapter<T, VH>(items, holderCreator, onRcvItemClickListener), DragAndDropAdapter {

    override fun onItemMove(positionFrom: Int, positionTo: Int) {
        if (positionFrom < positionTo) {
            for (i in positionFrom until positionTo) {
                Collections.swap(getItems(), i, i + 1)

            }
        } else {
            for (i in positionFrom downTo positionTo + 1) {
                Collections.swap(getItems(), i, i - 1)
            }
        }
        notifyItemMoved(positionFrom, positionTo)
    }

}
