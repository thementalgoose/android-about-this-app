package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.presentation.components.AppVersion
import tmg.aboutthisapp.presentation.components.DependencyItem
import tmg.aboutthisapp.presentation.components.Header
import tmg.aboutthisapp.presentation.components.LinkItem
import tmg.aboutthisapp.utils.PreviewPhone

@Composable
internal fun ScreenCompact(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    dependencyClicked: (Dependency) -> Unit,
    showBack: Boolean = false,
    contactEmail: String? = null,
    links: List<Link> = emptyList(),
    backClicked: () -> Unit = { },
    linksMaximumColumns: Int = 4,
    appVersion: String? = null,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(AboutThisAppTheme.colours.background)
            .testTag("Screen")
            .fillMaxSize(),
        columns = GridCells.Fixed(links.size.coerceIn(1, linksMaximumColumns)),
        content = {
            item(key = "header", span = { GridItemSpan(maxLineSpan) }) {
                Column(Modifier.fillMaxWidth()) {
                    if (showBack) {
                        IconButton(onClick = backClicked) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = stringResource(AboutThisAppTheme.strings.accessibilityBack),
                                tint = AboutThisAppTheme.colours.onBackground
                            )
                        }
                    }
                    Header(
                        appIcon = appIcon,
                        appName = appName,
                        contactEmail = contactEmail
                    )
                }
            }

            items(links, key = { "link-${it.label}"} ) {
                LinkItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .clickable(onClick = it.onClick),
                    label = it.label,
                    icon = it.icon,
                    tint = it.tint
                )
            }


            if (header != null) {
                item(key = "content-header", span = { GridItemSpan(maxLineSpan) }) {
                    Column(Modifier.fillMaxWidth()) {
                        header.invoke(this)
                    }
                }
            }

            items(dependencies, key = { "${it.dependencyName}-${it.url}"}, span = { GridItemSpan(maxLineSpan) }) {
                DependencyItem(
                    modifier = Modifier
                        .padding(
                            horizontal = medium,
                            vertical = small
                        )
                        .clip(RoundedCornerShape(6.dp))
                        .clickable(
                            onClick = { dependencyClicked(it) }
                        ),
                    name = it.dependencyName,
                    author = it.author,
                    url = it.url,
                    icon = it.icon,
                )
            }

            if (footer != null) {
                item(key = "content-footer", span = { GridItemSpan(maxLineSpan) }) {
                    Column(Modifier.fillMaxWidth()) {
                        footer.invoke(this)
                    }
                }
            }

            item(key = "app-version", span = { GridItemSpan(maxLineSpan) }) {
                AppVersion(
                    modifier = Modifier.padding(
                        horizontal = medium,
                        vertical = medium,
                    ),
                    appVersion = appVersion,
                )
            }
        }
    )
}


@PreviewPhone
@Composable
private fun PreviewPhone() {
    AboutThisAppTheme {
        PreviewScreenCompat()
    }
}