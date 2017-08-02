package com.helper.revern.home

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.helper.revern.home_two.HomeTwoController

/**
 * Created by Revern on 31.07.2017.
 */
class ConductorAdapter(host: Controller) : RouterPagerAdapter(host) {

    val PAGE_COLORS = intArrayOf(android.R.color.holo_red_dark,
            android.R.color.holo_green_dark, android.R.color.holo_blue_dark)

    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            val page = HomeTwoController("hello $position", PAGE_COLORS[position])
            router.setRoot(RouterTransaction.with(page))
        }
    }

    override fun getCount(): Int {
        return PAGE_COLORS.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "title $position"
    }
}