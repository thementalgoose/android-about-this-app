package tmg.components.about.viewholders

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tmg.components.R
import tmg.components.about.AboutThisAppCallback
import tmg.components.about.AboutThisAppItem

internal class AboutThisAppViewHolderMessage(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.aboutThisAppMessage_title)

    fun bind(item: AboutThisAppItem.Message) {
        title.gravity = if (item.isCentered) Gravity.CENTER else Gravity.START
        title.text = item.msg
    }
}