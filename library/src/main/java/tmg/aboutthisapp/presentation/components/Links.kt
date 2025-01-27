package tmg.aboutthisapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.R
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
internal fun LinkItem(
    @StringRes
    label: Int,
    @DrawableRes
    icon: Int,
    modifier: Modifier = Modifier,
    tint: Boolean = false,
) {
    Column(
        modifier = modifier
            .padding(
                vertical = small,
                horizontal = medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (tint) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = AboutThisAppTheme.colours.onBackground
            )
        } else {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
            )
        }

        Text(
            style = LocalTypography.current.body1,
            modifier = Modifier.padding(top = small),
            text = stringResource(id = label),
            textAlign = TextAlign.Center,
            color = AboutThisAppTheme.colours.onBackground
        )
    }
}

@PreviewTheme
@Composable
private fun Preview(
    @PreviewParameter(IconParameterProvider::class) data: Pair<Int, Int>
) {
    val (stringRes, iconRes) = data
    AboutThisAppTheme {
        LinkItem(
            label = stringRes,
            icon = iconRes,
            tint = true
        )
    }
}

private class IconParameterProvider : PreviewParameterProvider<Pair<Int, Int>> {
    override val values: Sequence<Pair<Int, Int>> = sequenceOf(
        R.string.about_this_app_github to R.drawable.ic_util_icon_github,
        R.string.about_this_app_gitlab to R.drawable.ic_util_icon_gitlab,
        R.string.about_this_app_play to R.drawable.ic_util_icon_play,
        R.string.about_this_app_email to R.drawable.ic_util_icon_email,
        R.string.about_this_app_linkedin to R.drawable.ic_util_icon_linkedin,
        R.string.about_this_app_website to R.drawable.ic_util_icon_website,
        R.string.about_this_app_reddit to R.drawable.ic_util_icon_reddit,
        R.string.about_this_app_twitter to R.drawable.ic_util_icon_twitter,
        R.string.about_this_app_youtube to R.drawable.ic_util_icon_youtube
    )
}