package tmg.components.prefs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.app_preferences_category.view.tvTitle
import kotlinx.android.synthetic.main.app_preferences_preference.view.clMain
import kotlinx.android.synthetic.main.app_preferences_preference.view.tvDescription
import kotlinx.android.synthetic.main.app_preferences_preference_switch.view.*
import tmg.components.BuildConfig
import tmg.components.R
import tmg.components.prefs.AppPreferencesItem.SwitchPreference
import tmg.components.utils.toEnum

open class AppPreferencesAdapter(
    val prefClicked: (prefKey: String) -> Unit = { _ -> },
    val prefSwitchClicked: (prefKey: String, newState: Boolean) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<AppPreferencesItem> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(DiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    //region Override methods

    open val categoryLayoutId: Int = R.layout.app_preferences_category

    open val preferenceLayoutId: Int = R.layout.app_preferences_preference

    open val preferenceSwitchLayoutId: Int = R.layout.app_preferences_preference_switch

    open val preferenceFooterLayoutId: Int = R.layout.app_preferences_footer

    open fun bindCategory(view: View, model: AppPreferencesItem.Category) {
        view.apply {
            this.tvTitle.setText(model.title)
        }
    }

    open fun bindPreference(view: View, model: AppPreferencesItem.Preference) {
        view.apply {
            this.clMain.setOnClickListener {
                prefClicked(model.prefKey)
            }
            this.tvTitle.setText(model.title)
            this.tvDescription.setText(model.description)
        }
    }

    open fun bindPreferenceSwitch(view: View, model: SwitchPreference) {
        view.apply {
            this.tvTitle.setText(model.title)
            this.tvDescription.setText(model.description)
            this.switchWidget.isChecked = model.isChecked

            this.clMain.setOnClickListener {
                switchChecked(model.prefKey, !model.isChecked)
            }
        }
    }

    open fun bindPreferenceFooter(view: View, model: AppPreferencesItem.Footer) {
        view.apply {
            this.tvTitle.text = model.version
        }
    }

    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType.toEnum<AppPreferencesItemType>()) {
            AppPreferencesItemType.CATEGORY -> AppPreferencesViewHolder(
                LayoutInflater.from(parent.context).inflate(categoryLayoutId, parent, false)
            )
            AppPreferencesItemType.PREF -> AppPreferencesViewHolder(
                LayoutInflater.from(parent.context).inflate(preferenceLayoutId, parent, false)
            )
            AppPreferencesItemType.PREF_SWITCH -> AppPreferencesViewHolder(
                LayoutInflater.from(parent.context).inflate(preferenceSwitchLayoutId, parent, false)
            )
            AppPreferencesItemType.FOOTER -> AppPreferencesViewHolder(
                LayoutInflater.from(parent.context).inflate(preferenceFooterLayoutId, parent, false)
            )
            null -> throw Error("Type not supported!")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when (getItemViewType(position).toEnum<AppPreferencesItemType>()) {
            AppPreferencesItemType.CATEGORY -> {
                bindCategory(holder.itemView, list[position] as AppPreferencesItem.Category)
            }
            AppPreferencesItemType.PREF -> {
                bindPreference(holder.itemView, list[position] as AppPreferencesItem.Preference)
            }
            AppPreferencesItemType.PREF_SWITCH -> {
                bindPreferenceSwitch(
                    holder.itemView,
                    list[position] as SwitchPreference
                )
            }
            AppPreferencesItemType.FOOTER -> {
                bindPreferenceFooter(
                    holder.itemView,
                    list[position] as AppPreferencesItem.Footer
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

    override fun getItemCount(): Int = list.size

    fun switchChecked(key: String, isChecked: Boolean) {
        prefSwitchClicked(key, isChecked)
        updateSwitch(key, isChecked)
    }

    private fun updateSwitch(key: String, isChecked: Boolean) {
        val newList: List<AppPreferencesItem> = list
            .map {
                if (it is SwitchPreference && it.prefKey == key) {
                    return@map SwitchPreference(it.prefKey, it.title, it.description, isChecked)
                }
                return@map it
            }
        this.list = newList
    }

    //region DiffCallback

    inner class DiffCallback(
        private val oldList: List<AppPreferencesItem>,
        private val newList: List<AppPreferencesItem>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(old: Int, new: Int): Boolean =
            if (oldList[old] is SwitchPreference && newList[new] is SwitchPreference) {
                (oldList[old] as SwitchPreference).prefKey == (newList[old] as SwitchPreference).prefKey
            }
            else {
                oldList[old] == newList[new]
            }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size
    }

    //endregion
}