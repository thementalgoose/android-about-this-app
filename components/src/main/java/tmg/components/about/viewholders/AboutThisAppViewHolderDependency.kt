package tmg.components.about.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tmg.components.R
import tmg.components.about.AboutThisAppCallback
import tmg.components.about.AboutThisAppDependency
import tmg.components.about.AboutThisAppItem

internal class AboutThisAppViewHolderDependency(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val clAboutThisAppDependencyMain: ConstraintLayout = itemView.findViewById(R.id.aboutThisAppDependency_container)
    private val imgAboutThisAppDependencyIcon: ImageView = itemView.findViewById(R.id.aboutThisAppDependency_icon)
    private val tvAboutThisAppDependencyName: TextView = itemView.findViewById(R.id.aboutThisAppDependency_name)
    private val tvAboutThisAppDependencyAuthor: TextView = itemView.findViewById(R.id.aboutThisAppDependency_author)
    private val tvAboutThisAppDependencyUrl: TextView = itemView.findViewById(R.id.aboutThisAppDependency_url)

    private lateinit var dependency: AboutThisAppDependency

    init {
        clAboutThisAppDependencyMain.setOnClickListener(this)
    }

    fun bind(item: AboutThisAppItem.Dependency) {
        this.dependency = item.item
        tvAboutThisAppDependencyName.text = dependency.dependencyName
        tvAboutThisAppDependencyAuthor.text = dependency.author
        tvAboutThisAppDependencyUrl.text = dependency.url

        Glide.with(itemView)
            .load(dependency.imageUrl)
            .into(imgAboutThisAppDependencyIcon)
    }

    //region View.OnClickListener

    override fun onClick(v: View?) {
        callback.dependencyItemClicked(dependency)
    }

    //endregion
}