package com.helper.revern.base

import com.arellomobile.mvp.MvpPresenter

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>()
