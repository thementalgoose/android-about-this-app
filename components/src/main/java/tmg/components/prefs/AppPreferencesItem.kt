package tmg.components.prefs

sealed class AppPreferencesItem(
    val type: AppPreferencesItemType
) {
    data class Category(
        val title: String
    ): AppPreferencesItem(AppPreferencesItemType.CATEGORY)

    data class Preference(
        val prefKey: String,
        val title: String,
        val description: String
    ): AppPreferencesItem(AppPreferencesItemType.PREF)

    data class SwitchPreference(
        val prefKey: String,
        val title: String,
        val description: String,
        var isChecked: Boolean
    ): AppPreferencesItem(AppPreferencesItemType.PREF_SWITCH)
}