package com.helper.revern.home

import com.arellomobile.mvp.InjectViewState
import com.helper.revern.base.BasePresenter

@InjectViewState
class HomePresenter : BasePresenter<HomeView>() {
    fun changeTitle() {
        viewState.changeTitle("Base is Working")
    }
}