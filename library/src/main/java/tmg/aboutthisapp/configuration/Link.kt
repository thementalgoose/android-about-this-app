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
        fun github(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_github,
            icon = R.drawable.ic_util_icon_github,
            onClick = onClick,
        )

        @JvmStatic
        fun gitlab(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_gitlab,
            icon = R.drawable.ic_util_icon_gitlab,
            onClick = onClick,
        )

        @JvmStatic
        fun linkedIn(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_linkedin,
            icon = R.drawable.ic_util_icon_linkedin,
            onClick = onClick,
        )

        @JvmStatic
        fun reddit(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_reddit,
            icon = R.drawable.ic_util_icon_reddit,
            onClick = onClick,
        )

        @JvmStatic
        fun twitter(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_twitter,
            icon = R.drawable.ic_util_icon_twitter,
            onClick = onClick,
        )

        @JvmStatic
        fun x(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_x,
            icon = R.drawable.ic_util_icon_x,
            onClick = onClick,
        )

        @JvmStatic
        fun youtube(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_youtube,
            icon = R.drawable.ic_util_icon_youtube,
            onClick = onClick,
        )

        @JvmStatic
        fun email(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_email,
            icon = R.drawable.ic_util_icon_email,
            onClick = onClick,
        )

        @JvmStatic
        fun play(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_play,
            icon = R.drawable.ic_util_icon_play,
            onClick = onClick,
        )

        @JvmStatic
        fun website(onClick: () -> Unit) = Link(
            label = R.string.about_this_app_website,
            icon = R.drawable.ic_util_icon_website,
            onClick = onClick,
        )
    }
}