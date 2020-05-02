package tmg.components.prefs

import android.content.Context
import androidx.annotation.StringRes

open class AppPreferencesItemBuilder(
    val context: Context
) {
    fun string(@StringRes value: Int): String = context.getString(value)

    var list: MutableList<AppPreferencesItem> = mutableListOf()

    fun add(item: AppPreferencesItem) {
        list.add(item)
    }

    fun addAll(items: List<AppPreferencesItem>) {
        list.addAll(items)
    }
}

class AppPreferencesItemCategory(context: Context): AppPreferencesItemBuilder(context)

class AppPreferencesItemScreen(context: Context): AppPreferencesItemBuilder(context)

fun prefsList(context: Context, block: AppPreferencesItemScreen.() -> Unit): MutableList<AppPreferencesItem> {
    val screenHolder = AppPreferencesItemScreen(context)
    block(screenHolder)
    return screenHolder.list
}

fun AppPreferencesItemScreen.category(title: String, block: AppPreferencesItemCategory.() -> Unit) {
    this.add(AppPreferencesItem.Category(title))
    val holder = AppPreferencesItemCategory(context = context)
    block(holder)
    this.addAll(holder.list)
}

fun AppPreferencesItemScreen.category(@StringRes title: Int, block: AppPreferencesItemCategory.() -> Unit) {
    this.add(AppPreferencesItem.Category(string(title)))
    val holder = AppPreferencesItemCategory(context = context)
    block(holder)
    this.addAll(holder.list)
}

fun AppPreferencesItemCategory.preference(prefsKey: String, title: String, description: String) {
    add(AppPreferencesItem.Preference(
        prefKey = prefsKey,
        title = title,
        description = description
    ))
}

fun AppPreferencesItemCategory.preference(prefsKey: String, @StringRes title: Int, @StringRes description: Int) {
    add(AppPreferencesItem.Preference(
        prefKey = prefsKey,
        title = string(title),
        description = string(description)
    ))
}

fun AppPreferencesItemCategory.switch(prefsKey: String, title: String, description: String, isChecked: Boolean) {
    add(AppPreferencesItem.SwitchPreference(
        prefKey = prefsKey,
        title = title,
        description = description,
        isChecked = isChecked
    ))
}

fun AppPreferencesItemCategory.switch(prefsKey: String, @StringRes title: Int, @StringRes description: Int, isChecked: Boolean) {
    add(AppPreferencesItem.SwitchPreference(
        prefKey = prefsKey,
        title = string(title),
        description = string(description),
        isChecked = isChecked
    ))
}