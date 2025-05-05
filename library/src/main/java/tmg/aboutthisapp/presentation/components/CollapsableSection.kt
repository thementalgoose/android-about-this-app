package tmg.aboutthisapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.R
import tmg.aboutthisapp.utils.PreviewTheme

@Composable
fun CollapsableSection(
    @StringRes
    titleRes: Int,
    expanded: Boolean,
    modifier: Modifier = Modifier,
) {
    val rotationDegrees = animateFloatAsState(
        label = "rotation",
        targetValue = if (expanded) 0f else 90f
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(titleRes),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AboutThisAppTheme.colours.onSurface
        )
        androidx.compose.material3.Icon(
            painter = painterResource(R.drawable.ic_about_this_app_section),
            tint = AboutThisAppTheme.colours.onPrimary,
            contentDescription = null,
            modifier = Modifier
                .rotate(rotationDegrees.value)
        )
    }
}

@PreviewTheme
@Composable
private fun PreviewHeaderCollapsed() {
    AboutThisAppTheme {
        Box(Modifier.padding(16.dp)) {
            CollapsableSection(
                titleRes = AboutThisAppTheme.strings.licensesHeader,
                expanded = false
            )
        }
    }
}

@PreviewTheme
@Composable
private fun PreviewHeaderExpanded() {
    AboutThisAppTheme {
        Box(Modifier.padding(16.dp)) {
            CollapsableSection(
                titleRes = AboutThisAppTheme.strings.licensesHeader,
                expanded = true
            )
        }
    }
}