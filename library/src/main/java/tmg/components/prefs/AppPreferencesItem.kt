package tmg.components.prefs

import androidx.annotation.StringRes

sealed class AppPreferencesItem(
    val type: AppPreferencesItemType
) {
    data class Category(
        @StringRes
        val title: Int
    ): AppPreferencesItem(AppPreferencesItemType.CATEGORY)

    data class Preference(
        val prefKey: String,
        @StringRes
        val title: Int,
        @StringRes
        val description: Int
    ): AppPreferencesItem(AppPreferencesItemType.PREF)

    data class SwitchPreference(
        val prefKey: String,
        @StringRes
        val title: Int,
        @StringRes
        val description: Int,
        var isChecked: Boolean
    ): AppPreferencesItem(AppPreferencesItemType.PREF_SWITCH)

    data class Footer(
        val version: String
    ): AppPreferencesItem(AppPreferencesItemType.FOOTER)
}