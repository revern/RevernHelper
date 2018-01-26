package com.helper.revern.home

import com.arellomobile.mvp.InjectViewState
import com.helper.revern.base.BasePresenter

@InjectViewState
class HomePresenter : BasePresenter<HomeView>() {
    fun changeTitle(text: String) {
        viewState.changeTitle(text)
    }
}