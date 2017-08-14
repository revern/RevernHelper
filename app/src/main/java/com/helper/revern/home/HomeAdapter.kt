package com.helper.revern.home

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter

/**
 * Created by Revern on 31.07.2017.
 */
class HomeAdapter(host: Controller) : RouterPagerAdapter(host) {

    val PAGES = ArrayList<Controller>()

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            val page = PAGES[position]
            router.setRoot(RouterTransaction.with(page))
        }
    }

    override fun getCount(): Int {
        return PAGES.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "title $position"
    }
}