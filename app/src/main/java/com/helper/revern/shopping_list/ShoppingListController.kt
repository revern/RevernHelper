package com.helper.revern.shopping_list

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.utils.ui.UiInfo

class ShoppingListController : BaseController(), ShoppingListView {

    @InjectPresenter(type = PresenterType.LOCAL) lateinit var presenter: ShoppingListPresenter

    override fun showShoppingList() {
    }

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_shopping_list, R.string.title_shopping_list)
    }

    override fun onCreateView(view: View) {

    }

}