package com.helper.revern.home

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.helper.revern.base.BaseController
import com.helper.revern.base.BaseView

interface HomeView : BaseView {

    @StateStrategyType(value = SkipStrategy::class)
    fun initPager(pages: ArrayList<BaseController>)

    @StateStrategyType(value = SkipStrategy::class)
    fun initPager(pages: ArrayList<BaseController>, position: Int)

    @StateStrategyType(value = SkipStrategy::class)
    fun addPage(page: BaseController)

    @StateStrategyType(value = SkipStrategy::class)
    fun showError(errorText: String)

    @StateStrategyType(value = SkipStrategy::class)
    fun showError(errorTextRes: Int)

}