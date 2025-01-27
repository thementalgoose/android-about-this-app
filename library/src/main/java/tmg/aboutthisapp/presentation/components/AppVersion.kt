package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
internal fun AppVersion(
    modifier: Modifier = Modifier,
    appVersion: String? = null
) {
    if (appVersion != null) {
        Column(modifier = modifier) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    style = LocalTypography.current.body2,
                    text = "${stringResource(AboutThisAppTheme.strings.appVersion)}:",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = AboutThisAppTheme.colours.onBackground
                )

                Text(
                    style = LocalTypography.current.body2,
                    modifier = Modifier.padding(start = small),
                    text = appVersion,
                    maxLines = 1,
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
        AppVersion(
            appVersion = "1.0.0"
        )
    }
}