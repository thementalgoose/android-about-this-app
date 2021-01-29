package tmg.components.about.viewholders

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.about_this_app_item_view_message.view.*
import tmg.components.R
import tmg.components.about.AboutThisAppCallback
import tmg.components.about.AboutThisAppItem
import tmg.components.utils.getColor

internal class AboutThisAppViewHolderMessage(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    fun bind(item: AboutThisAppItem.Message) {
        itemView.aboutThisAppMessage_title.gravity = if (item.isCentered) Gravity.CENTER else Gravity.START
        itemView.aboutThisAppMessage_title.text = item.msg
    }
}