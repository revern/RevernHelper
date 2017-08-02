package com.helper.revern.home_two

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by Revern on 01.08.2017.
 */
@InjectViewState
class HomeTwoPresenter : MvpPresenter<HomeTwoView>() {

    var clicksCount = 0
    fun showMessage() {
        clicksCount++
        viewState.showMessage("AZAZA from Presenter Bugagagagagag $clicksCount")
    }
}