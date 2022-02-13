package tmg.aboutthisapp.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tmg.aboutthisapp.R
import tmg.aboutthisapp.AboutThisAppCallback
import tmg.aboutthisapp.AboutThisAppItem
import tmg.aboutthisapp.utils.show

internal class AboutThisAppViewHolderHeader(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val email: TextView = itemView.findViewById(R.id.aboutThisAppLinks_email)
    private val github: TextView = itemView.findViewById(R.id.aboutThisAppLinks_github)
    private val play: TextView = itemView.findViewById(R.id.aboutThisAppLinks_play)
    private val website: TextView = itemView.findViewById(R.id.aboutThisAppLinks_website)

    init {
        email.setOnClickListener(this)
        github.setOnClickListener(this)
        play.setOnClickListener(this)
        website.setOnClickListener(this)
    }

    private lateinit var item: AboutThisAppItem.Header

    fun bind(item: AboutThisAppItem.Header) {
        this.item = item
        email.show(item.email != null)
        github.show(item.github != null)
        play.show(item.play != null)
        website.show(item.website != null)
    }

    override fun onClick(v: View?) {
        when (v) {
            email -> {
                callback.sendEmail(item.email, item.appName ?: "")
            }
            github -> {
                callback.clickUrl(item.github)
            }
            play -> {
                callback.clickUrl(item.play)
            }
            website -> {
                callback.clickUrl(item.website)
            }
        }
    }
}