package com.helper.revern.home

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.helper.revern.base.BaseController

class HomeAdapter(host: Controller, private val pages: ArrayList<BaseController>) : RouterPagerAdapter(host) {

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            val page = pages[position]
            router.setRoot(RouterTransaction.with(page))
        }
    }

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence = pages[position].getUiInfo().title

    fun getPageAt(position: Int): BaseController = pages[position]

    fun addPage(page: BaseController): Int {
        pages.add(count, page)
        notifyDataSetChanged()
        return count - 1
    }

}