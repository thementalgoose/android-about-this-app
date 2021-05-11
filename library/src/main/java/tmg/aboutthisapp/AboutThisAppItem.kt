package tmg.aboutthisapp

import androidx.annotation.LayoutRes

internal sealed class AboutThisAppItem(
    @LayoutRes val layoutId: Int
) {
    data class Dependency(
        val item: AboutThisAppDependency
    ): AboutThisAppItem(R.layout.about_this_app_item_view_dependency_item)

    data class Message(
        val msg: String,
        val isCentered: Boolean = true,
        val isPrimary: Boolean = true
    ): AboutThisAppItem(R.layout.about_this_app_item_view_message)

    data class Header(
        val play: String? = null,
        val email: String? = null,
        val website: String? = null,
        val github: String? = null
    ): AboutThisAppItem(R.layout.about_this_app_item_view_header)
}