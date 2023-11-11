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
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.DependencyIcon
import tmg.aboutthisapp.utils.PreviewTheme

private val iconSize: Dp = 42.dp

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
        .padding(horizontal = medium, vertical = small)
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
                        .background(Color(icon.backgroundColor))
                )
            }
            is DependencyIcon.Image -> {
                AsyncImage(
                    model = icon.url,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clip(CircleShape)
                        .background(icon.backgroundColor?.let { Color(it) } ?: AboutThisAppTheme.colours.colorPrimary)
                        .size(iconSize)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = name,
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
                    .padding(top = 2.dp, bottom = 2.dp),
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
        val colour = 0xFF972948
        DependencyItem(
            name = "Jetpack",
            author = "Google",
            url = "https://developer.google.com/android",
            icon = DependencyIcon.Icon(icon = R.drawable.ic_util_icon_website, backgroundColor = colour.toInt())
        )
    }
}