package tmg.components.about.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.about_this_app_item_view_header.view.*
import tmg.components.about.AboutThisAppCallback
import tmg.components.about.AboutThisAppItem
import tmg.components.utils.show

internal class AboutThisAppViewHolderHeader(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.aboutThisAppLinks_email.setOnClickListener(this)
        itemView.aboutThisAppLinks_github.setOnClickListener(this)
        itemView.aboutThisAppLinks_play.setOnClickListener(this)
        itemView.aboutThisAppLinks_website.setOnClickListener(this)
    }

    fun bind(item: AboutThisAppItem.Header) {
        itemView.aboutThisAppLinks_email.show(item.email != null)
        itemView.aboutThisAppLinks_github.show(item.github != null)
        itemView.aboutThisAppLinks_play.show(item.play != null)
        itemView.aboutThisAppLinks_website.show(item.website != null)
    }

    override fun onClick(v: View?) {
        when (v) {
            itemView.aboutThisAppLinks_email -> {
                callback.clickEmail()
            }
            itemView.aboutThisAppLinks_github -> {
                callback.clickGithub()
            }
            itemView.aboutThisAppLinks_play -> {
                callback.clickPlay()
            }
            itemView.aboutThisAppLinks_website -> {
                callback.clickWebsite()
            }
        }
    }
}