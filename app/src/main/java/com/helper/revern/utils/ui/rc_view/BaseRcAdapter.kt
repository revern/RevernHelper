package com.helper.revern.utils.ui.rc_view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import rx.functions.Func1

/**
 * Created by Revern on 04.08.2017.
 */
open class BaseRcAdapter<T, VH : BaseRcHolder<T>> : RecyclerView.Adapter<VH> {

    var items: MutableList<T>
    var func0: Func1<ViewGroup, VH>
    var onItemClickListener: OnRcItemClickListener<T>?

    constructor(items: MutableList<T>, func0: Func1<ViewGroup, VH>) : this(items, func0, null)

    constructor(items: MutableList<T>, func0: Func1<ViewGroup, VH>, onRcItemClickListener: OnRcItemClickListener<T>?) {
        this.items = items
        this.func0 = func0
        this.onItemClickListener = onRcItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        var holder = func0.call(parent)
        holder.itemView.setOnClickListener { v ->
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION)
                onItemClickListener?.onItemClick(getData()[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getData()[position])

    override fun getItemCount() = items.size

    fun getData(): List<T> {
        return items
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

}