package tmg.aboutthisapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.large
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.AboutThisAppTheme.dimens.xsmall
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Typography
import tmg.aboutthisapp.utils.PreviewTheme

private val iconSize: Dp = 84.dp

@Composable
internal fun Header(
    @DrawableRes
    appIcon: Int,
    appName: String,
    contactEmail: String?,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .padding(
            start = medium,
            end = medium,
            top = medium,
            bottom = large
        )
    ) {
        AsyncImage(
            model = appIcon,
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = appName,
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(
                start = medium,
                top = xsmall
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = AboutThisAppTheme.colours.onBackground,
                text = appName,
                style = LocalTypography.current.header,
                fontWeight = FontWeight.Bold
            )

            if (contactEmail != null) {
                Spacer(Modifier.height(small))
                Text(
                    style = LocalTypography.current.body1,
                    text = stringResource(AboutThisAppTheme.strings.contactEmail),
                    color = AboutThisAppTheme.colours.onBackground
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    style = LocalTypography.current.body2,
                    text = contactEmail,
                    color = AboutThisAppTheme.colours.onBackground
                )
            }
        }
    }
}

@PreviewTheme
@Composable
private fun Preview() {
    AboutThisAppTheme {
        Header(
            appIcon = R.drawable.ic_util_icon_github,
            appName = "My Application",
            contactEmail = "thementalgoose@gmail.com",
        )
    }
}