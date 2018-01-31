package com.helper.revern.new_list

import android.content.res.Resources
import android.view.View
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.helper.revern.App
import com.helper.revern.R
import com.helper.revern.base.BaseController
import com.helper.revern.utils.Strings
import com.helper.revern.utils.ui.EditTextDialog
import com.helper.revern.utils.ui.UiInfo
import rx.functions.Func1

class NewListController : BaseController(), NewListView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: NewListPresenter

    override fun getUiInfo(): UiInfo {
        return UiInfo(R.layout.screen_new_list, App.context().getString(R.string.title_new_list))
    }

    override fun onCreateView(view: View) {

    }

    @OnClick(R.id.add_list)
    fun onAddListClick(view: View) {
        EditTextDialog.show(activity!!, R.string.dialog_title_add_list, Func1 {
            text -> if (!Strings.isEmty(text)) presenter.addList(text)
        })
    }

}