package com.helper.revern.home

import com.arellomobile.mvp.InjectViewState
import com.helper.revern.utils.ui.BasePresenter

/**
 * Created by Revern on 01.08.2017.
 */
@InjectViewState
class HomePresenter : BasePresenter<HomeView>() {
    fun changeTitle() {
        viewState.changeTitle("Base is Working")
    }
}