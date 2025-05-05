@file:OptIn(ExperimentalMaterial3Api::class)

package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.configuration.License
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
internal fun License(
    model: License,
    modifier: Modifier = Modifier
) {
    val show = remember { mutableStateOf(false) }

    Row(modifier = modifier
        .clickable {
            show.value = !show.value
        }
        .clip(RoundedCornerShape(6.dp))
        .background(AboutThisAppTheme.colours.primary)
        .padding(horizontal = medium, vertical = small)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = model.label(),
            maxLines = 1,
            color = AboutThisAppTheme.colours.onPrimary,
            style = LocalTypography.current.body1,
        )
    }

    if (show.value) {
        BasicAlertDialog(
            onDismissRequest = {
                show.value = false
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(medium)
                        .clip(RoundedCornerShape(6.dp))
                        .background(AboutThisAppTheme.colours.background)
                        .verticalScroll(rememberScrollState()),
                ) {
                    when (model) {
                        is License.Text -> {
                            Text(
                                modifier = Modifier
                                    .padding(medium)
                                    .fillMaxWidth(),
                                text = model.text,
                                color = AboutThisAppTheme.colours.onPrimary,
                                style = LocalTypography.current.body1,
                            )
                        }
                        is License.Url -> {
                            Text(
                                modifier = Modifier
                                    .padding(medium)
                                    .fillMaxWidth(),
                                text = model.url,
                                color = AboutThisAppTheme.colours.onPrimary,
                                style = LocalTypography.current.body1,
                            )
                        }
                    }
                }
            }
        )
    }
}

@PreviewTheme
@Composable
private fun PreviewItem() {
    AboutThisAppTheme {
        Box(Modifier.padding(16.dp)) {
            License.Url(
                label = "License",
                url = "https://www.google.com"
            )
        }
    }
}