package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.configuration.AboutThisAppStrings

@Composable
internal fun AppVersion(
    modifier: Modifier = Modifier,
    appVersion: String? = null
) {
    Column(modifier = modifier
        .padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
        .fillMaxWidth()
    ) {
        if (appVersion != null) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${AboutThisAppTheme.strings.appVersion}:",
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