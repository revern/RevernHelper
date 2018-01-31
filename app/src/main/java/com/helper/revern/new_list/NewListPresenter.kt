package com.helper.revern.new_list

import com.arellomobile.mvp.InjectViewState
import com.helper.revern.base.BasePresenter
import com.helper.revern.new_list.events.AddNewListEvent
import org.greenrobot.eventbus.EventBus

@InjectViewState
class NewListPresenter : BasePresenter<NewListView>() {

    fun addList(listName: String) {
        EventBus.getDefault().post(AddNewListEvent(listName))
    }

}