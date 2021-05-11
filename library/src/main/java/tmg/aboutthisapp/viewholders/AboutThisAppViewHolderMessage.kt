package tmg.aboutthisapp.viewholders

import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tmg.aboutthisapp.R
import tmg.aboutthisapp.AboutThisAppCallback
import tmg.aboutthisapp.AboutThisAppItem

internal class AboutThisAppViewHolderMessage(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.aboutThisAppMessage_title)

    fun bind(item: AboutThisAppItem.Message) {

        when (item.isPrimary) {
            true -> {
                title.setTypeface(null, Typeface.NORMAL)
                title.alpha = 1.0f
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            false -> {
                title.setTypeface(null, Typeface.ITALIC)
                title.alpha = 0.4f
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
            }
        }

        title.gravity = if (item.isCentered) Gravity.CENTER else Gravity.START
        title.text = item.msg
    }
}