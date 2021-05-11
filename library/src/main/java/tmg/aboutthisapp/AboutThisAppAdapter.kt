package tmg.aboutthisapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmg.aboutthisapp.viewholders.AboutThisAppViewHolderDependency
import tmg.aboutthisapp.viewholders.AboutThisAppViewHolderHeader
import tmg.aboutthisapp.viewholders.AboutThisAppViewHolderMessage

internal class AboutThisAppAdapter(
    private val callback: AboutThisAppCallback
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<AboutThisAppItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.about_this_app_item_view_dependency_item -> {
                AboutThisAppViewHolderDependency(callback, view)
            }
            R.layout.about_this_app_item_view_header -> {
                AboutThisAppViewHolderHeader(callback, view)
            }
            R.layout.about_this_app_item_view_message -> {
                AboutThisAppViewHolderMessage(callback, view)
            }
            else -> throw RuntimeException("View type not supported by this adapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AboutThisAppViewHolderDependency -> holder.bind(items[position] as AboutThisAppItem.Dependency)
            is AboutThisAppViewHolderHeader -> holder.bind(items[position] as AboutThisAppItem.Header)
            is AboutThisAppViewHolderMessage -> holder.bind(items[position] as AboutThisAppItem.Message)
        }
    }

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun getItemCount(): Int = items.size
}