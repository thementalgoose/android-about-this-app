package tmg.aboutthisapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
internal fun LinkItem(
    @StringRes
    label: Int,
    @DrawableRes
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .clickable { onClick() },
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
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = label),
            textAlign = TextAlign.Center,
            color = AboutThisAppTheme.colours.onBackground
        )
    }
}

@PreviewTheme
@Composable
private fun Preview() {
    MaterialTheme {
        Link(
            label = R.string.about_this_app_github,
            icon = R.drawable.ic_util_icon_github,
            onClick = {  }
        )
    }
}