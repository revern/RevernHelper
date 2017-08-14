package com.helper.revern.utils.ui.rc_view

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

/**
 * Created by Revern on 04.08.2017.
 */
abstract class BaseRcHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        ButterKnife.bind(this, itemView)
    }

    abstract fun bind(item: T)

}