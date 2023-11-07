package tmg.aboutthisapp.configuration

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tmg.aboutthisapp.R

data class Link(
    @StringRes
    val label: Int,
    @DrawableRes
    val icon: Int,
    val tint: Boolean = true,
    val onClick: () -> Unit,
) {
    companion object {
        @JvmStatic
        fun Github(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_github,
            icon = R.drawable.ic_util_icon_github,
            onClick = onClick,
        )

        @JvmStatic
        fun Email(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_send_email,
            icon = R.drawable.ic_util_icon_email,
            onClick = onClick,
        )

        @JvmStatic
        fun Play(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_play,
            icon = R.drawable.ic_util_icon_play,
            onClick = onClick,
        )

        @JvmStatic
        fun Website(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_website,
            icon = R.drawable.ic_util_icon_website,
            onClick = onClick,
        )
    }
}