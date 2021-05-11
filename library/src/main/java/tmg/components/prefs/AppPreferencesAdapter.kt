package tmg.components.prefs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import tmg.components.databinding.AppPreferencesCategoryBinding
import tmg.components.databinding.AppPreferencesFooterBinding
import tmg.components.databinding.AppPreferencesPreferenceBinding
import tmg.components.databinding.AppPreferencesPreferenceSwitchBinding
import tmg.components.prefs.AppPreferencesItem.SwitchPreference
import tmg.components.utils.toEnum

open class AppPreferencesAdapter(
    val prefClicked: (prefKey: String) -> Unit = { _ -> },
    val prefSwitchClicked: (prefKey: String, newState: Boolean) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<AppPreferencesViewHolder>() {

    var list: List<AppPreferencesItem> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(DiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    //region Override methods

    open fun categoryLayoutId(viewGroup: ViewGroup): ViewBinding = AppPreferencesCategoryBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    )

    open fun preferenceLayoutId(viewGroup: ViewGroup): ViewBinding = AppPreferencesPreferenceBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    )

    open fun preferenceSwitchLayoutId(viewGroup: ViewGroup): ViewBinding = AppPreferencesPreferenceSwitchBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    )

    open fun preferenceFooterLayoutId(viewGroup: ViewGroup): ViewBinding = AppPreferencesFooterBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    )

    open fun bindCategory(view: ViewBinding, model: AppPreferencesItem.Category) {

        (view as? AppPreferencesCategoryBinding)?.apply {
            this.tvTitle.setText(model.title)
        }
    }

    open fun bindPreference(view: ViewBinding, model: AppPreferencesItem.Preference) {
        (view as? AppPreferencesPreferenceBinding)?.apply {
            this.clMain.setOnClickListener {
                prefClicked(model.prefKey)
            }
            this.tvTitle.setText(model.title)
            this.tvDescription.setText(model.description)
        }
    }

    open fun bindPreferenceSwitch(view: ViewBinding, model: SwitchPreference) {
        (view as? AppPreferencesPreferenceSwitchBinding)?.apply {
            this.tvTitle.setText(model.title)
            this.tvDescription.setText(model.description)
            this.switchWidget.isChecked = model.isChecked
            this.clMain.setOnClickListener {
                switchChecked(model.prefKey, !model.isChecked)
            }
        }
    }

    open fun bindPreferenceFooter(view: ViewBinding, model: AppPreferencesItem.Footer) {
        (view as? AppPreferencesFooterBinding)?.apply {
            this.tvTitle.text = model.version
        }
    }

    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppPreferencesViewHolder {
        return when (viewType.toEnum<AppPreferencesItemType>()) {
            AppPreferencesItemType.CATEGORY -> AppPreferencesViewHolder(
                categoryLayoutId(parent)
            )
            AppPreferencesItemType.PREF -> AppPreferencesViewHolder(
                preferenceLayoutId(parent)
            )
            AppPreferencesItemType.PREF_SWITCH -> AppPreferencesViewHolder(
                preferenceSwitchLayoutId(parent)
            )
            AppPreferencesItemType.FOOTER -> AppPreferencesViewHolder(
                preferenceFooterLayoutId(parent)
            )
            null -> throw Error("Type not supported!")
        }
    }

    override fun onBindViewHolder(
        holder: AppPreferencesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        when (getItemViewType(position).toEnum<AppPreferencesItemType>()) {
            AppPreferencesItemType.CATEGORY -> {
                bindCategory(holder.binding, list[position] as AppPreferencesItem.Category)
            }
            AppPreferencesItemType.PREF -> {
                bindPreference(holder.binding, list[position] as AppPreferencesItem.Preference)
            }
            AppPreferencesItemType.PREF_SWITCH -> {
                bindPreferenceSwitch(
                    holder.binding,
                    list[position] as SwitchPreference
                )
            }
            AppPreferencesItemType.FOOTER -> {
                bindPreferenceFooter(
                    holder.binding,
                    list[position] as AppPreferencesItem.Footer
                )
            }
        }
    }

    override fun onBindViewHolder(holder: AppPreferencesViewHolder, position: Int) {
        this.onBindViewHolder(holder, position, mutableListOf())
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

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(old: Int, new: Int): Boolean {
            val oldItem = oldList[old]
            val newItem = newList[new]
            return oldItem == newItem || isSwitchPref(oldItem, newItem)
        }

        override fun areContentsTheSame(old: Int, new: Int): Boolean {
            return oldList[old] == newList[new]
        }

        private fun isSwitchPref(oldItem: AppPreferencesItem, newItem: AppPreferencesItem) =
            oldItem is SwitchPreference && newItem is SwitchPreference && oldItem.prefKey == newItem.prefKey
    }

    //endregion
}