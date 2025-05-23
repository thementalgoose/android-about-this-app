@file:OptIn(ExperimentalMaterial3Api::class)

package tmg.aboutthisapp.presentation.components

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
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
        ModalBottomSheet(
            containerColor = AboutThisAppTheme.colours.background,
            onDismissRequest = {
                show.value = false
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(medium)
                        .verticalScroll(rememberScrollState()),
                ) {
                    when (model) {
                        is License.Text -> {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = model.text,
                                    color = AboutThisAppTheme.colours.onPrimary,
                                    style = LocalTypography.current.body1,
                                    fontFamily = FontFamily(Typeface.MONOSPACE),
                                )
                            }
                        }
                        is License.Url -> {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState())
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = model.url,
                                    color = AboutThisAppTheme.colours.onPrimary,
                                    style = LocalTypography.current.body1,
                                    fontFamily = FontFamily(Typeface.MONOSPACE)
                                )
                            }
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