package tmg.components.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmg.components.R
import tmg.utilities.sortedlist.SortedListAdapter
import tmg.utilities.sortedlist.SortedListComparator


class AboutThisAppDependencyAdapter(
    private val callback: AboutThisAppDependencyCallback
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}


class AboutThisAppDependencyAdapter(
    private val callback: AboutThisAppDependencyCallback
): SortedListAdapter<AboutThisAppDependency, AboutThisAppDependencyViewHolder>(AboutThisAppDependency::class.java, AboutThisAppDependencyComparator()) {
    override fun onBindViewHolder(
        holder: AboutThisAppDependencyViewHolder,
        position: Int,
        item: AboutThisAppDependency
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AboutThisAppDependencyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.about_this_app_item_view_dependency_item, parent, false)
        return AboutThisAppDependencyViewHolder(callback, view)
    }
}

//region Comparator

class AboutThisAppDependencyComparator: SortedListComparator<AboutThisAppDependency> {
    override fun equal(obj1: AboutThisAppDependency, obj2: AboutThisAppDependency): Boolean {
        return obj1.dependencyName.equals(obj2.dependencyName)
    }

    override fun compare(o1: AboutThisAppDependency?, o2: AboutThisAppDependency?): Int {
        return o1!!.order.compareTo(o2!!.order)
    }
}

//endregion

//region Callback

interface AboutThisAppDependencyCallback {
    fun dependencyItemClicked(item: AboutThisAppDependency)
}