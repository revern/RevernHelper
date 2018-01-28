package com.helper.revern.home

import android.content.Context
import butterknife.internal.Utils.listOf
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.helper.revern.base.BaseController
import com.helper.revern.shopping_list.ShoppingListController
import com.helper.revern.tasks.TasksController

class HomeAdapter(host: Controller, private var context: Context?) : RouterPagerAdapter(host) {

    private val pages: MutableList<BaseController> = listOf(TasksController(), ShoppingListController())

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            val page = pages[position]
            router.setRoot(RouterTransaction.with(page))
        }
    }

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence =
            context?.getString(pages[position].getUiInfo().title) ?: ""

    fun getPageAt(position: Int): BaseController = pages[position]
}