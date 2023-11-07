package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme

@Composable
internal fun AppVersion(
    modifier: Modifier = Modifier,
    appVersion: String? = null
) {
    Column(modifier = modifier
        .padding(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 24.dp
        )
        .fillMaxWidth()
    ) {
        if (appVersion != null) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${stringResource(AboutThisAppTheme.strings.appVersion)}:",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = AboutThisAppTheme.colours.onBackground
                )

                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = appVersion,
                    maxLines = 1,
                    color = AboutThisAppTheme.colours.onBackground
                )
            }
        }
    }
}