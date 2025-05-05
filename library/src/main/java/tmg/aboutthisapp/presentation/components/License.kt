package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
internal fun License(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(6.dp))
        .background(AboutThisAppTheme.colours.primary)
        .padding(horizontal = medium, vertical = small)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = name,
            maxLines = 1,
            color = AboutThisAppTheme.colours.onPrimary,
            style = LocalTypography.current.body1,
        )
    }
}

@PreviewTheme
@Composable
private fun PreviewItem() {
    AboutThisAppTheme {
        Box(Modifier.padding(16.dp)) {
            License(
                name = "License",
            )
        }
    }
}