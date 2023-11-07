package tmg.aboutthisapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.R
import tmg.aboutthisapp.utils.PreviewTheme

private val iconSize: Dp = 42.dp

sealed class DependencyIcon {
    data class Image(
        val url: String,
    ): DependencyIcon()

    data class Icon(
        val icon: Int,
        val backgroundColor: Color
    ): DependencyIcon()
}

@Composable
internal fun DependencyItem(
    name: String,
    author: String,
    url: String,
    icon: DependencyIcon,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(6.dp))
        .background(AboutThisAppTheme.colours.primary)
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        when (icon) {
            is DependencyIcon.Icon -> {
                Icon(
                    painter = painterResource(id = icon.icon),
                    contentDescription = null,
                    tint = AboutThisAppTheme.colours.onPrimary,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(iconSize)
                        .clip(CircleShape)
                        .background(icon.backgroundColor)
                )
            }
            is DependencyIcon.Image -> {
                AsyncImage(
                    model = icon.url,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(AboutThisAppTheme.colours.colorPrimary)
                        .size(iconSize)
                        .clip(CircleShape)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = name,
                maxLines = 1,
                color = AboutThisAppTheme.colours.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                text = author,
                maxLines = 1,
                color = AboutThisAppTheme.colours.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                text = url,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AboutThisAppTheme.colours.onPrimary,
                fontSize = 14.sp
            )
        }
    }
}

@PreviewTheme
@Composable
private fun Preview() {
    AboutThisAppTheme {
        DependencyItem(
            name = "Jetpack",
            author = "Google",
            url = "https://developer.google.com/android",
            icon = DependencyIcon.Icon(icon = R.drawable.ic_util_icon_website, backgroundColor = Color.Magenta)
        )
    }
}