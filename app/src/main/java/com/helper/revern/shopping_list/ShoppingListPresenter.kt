package com.helper.revern.shopping_list

import com.arellomobile.mvp.InjectViewState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.helper.revern.base.BasePresenter
import com.helper.revern.shopping_list.models.ShoppingItem
import com.helper.revern.utils.Prefs
import java.util.*

@InjectViewState
class ShoppingListPresenter : BasePresenter<ShoppingListView>() {

    companion object {
        private val KEY_SHOPPING_LIST = "shopping_list"
    }

    private var shoppingList = ArrayList<ShoppingItem>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSavedShoppingList()
    }

    private fun loadSavedShoppingList() {
        val savedShoppingListJson: String? = Prefs.instance().getString(KEY_SHOPPING_LIST, null) ?: return

        val savedShoppingList : List<ShoppingItem> = Gson()
                .fromJson(savedShoppingListJson, object : TypeToken<List<ShoppingItem>>(){}.type)
        shoppingList.clear()
        shoppingList.addAll(savedShoppingList)
        viewState.showShoppingList()
    }
}