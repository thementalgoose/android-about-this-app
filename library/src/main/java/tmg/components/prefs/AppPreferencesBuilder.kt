package tmg.components.prefs

import android.content.Context
import androidx.annotation.StringRes

open class AppPreferencesItemBuilder {

    var list: MutableList<AppPreferencesItem> = mutableListOf()

    fun add(item: AppPreferencesItem) {
        list.add(item)
    }

    fun addAll(items: List<AppPreferencesItem>) {
        list.addAll(items)
    }
}

class AppPreferencesItemCategory : AppPreferencesItemBuilder()

class AppPreferencesItemScreen : AppPreferencesItemBuilder()

fun prefsList(block: AppPreferencesItemScreen.() -> Unit): MutableList<AppPreferencesItem> {
    val screenHolder = AppPreferencesItemScreen()
    block(screenHolder)
    return screenHolder.list
}


fun AppPreferencesItemScreen.category(@StringRes title: Int, block: AppPreferencesItemCategory.() -> Unit) {
    this.add(AppPreferencesItem.Category(title))
    val holder = AppPreferencesItemCategory()
    block(holder)
    this.addAll(holder.list)
}

fun AppPreferencesItemCategory.preference(prefsKey: String, @StringRes title: Int, @StringRes description: Int) {
    add(AppPreferencesItem.Preference(
        prefKey = prefsKey,
        title = title,
        description = description
    ))
}

fun AppPreferencesItemScreen.footer(version: String) {
    add(AppPreferencesItem.Footer(
        version = version
    ))
}

fun AppPreferencesItemCategory.switch(prefsKey: String, @StringRes title: Int, @StringRes description: Int, isChecked: Boolean) {
    add(AppPreferencesItem.SwitchPreference(
        prefKey = prefsKey,
        title = title,
        description = description,
        isChecked = isChecked
    ))
}