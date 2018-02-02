package com.helper.revern.home

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.helper.revern.base.BaseController
import com.helper.revern.base.BaseView

interface HomeView : BaseView {

    fun initPager(pages: ArrayList<BaseController>)

    fun selectPage(position: Int)

    @StateStrategyType(value = SkipStrategy::class)
    fun addPage(page: BaseController)

    fun showError(errorText: String)

    fun showError(errorTextRes: Int)

}