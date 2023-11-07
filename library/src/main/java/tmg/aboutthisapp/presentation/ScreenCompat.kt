package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.R
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.AboutThisAppStrings
import tmg.aboutthisapp.presentation.components.AppVersion
import tmg.aboutthisapp.presentation.components.DependencyIcon
import tmg.aboutthisapp.presentation.components.DependencyItem
import tmg.aboutthisapp.presentation.components.Header
import tmg.aboutthisapp.presentation.components.LinkItem

@Composable
internal fun ScreenCompact(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    showBack: Boolean = false,
    contactEmail: String? = null,
    links: List<Link> = emptyList(),
    backClicked: () -> Unit = { },
    linksColumns: Int = 4,
    appVersion: String? = null,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .background(AboutThisAppTheme.colours.background)
            .fillMaxSize(),
        columns = GridCells.Fixed(linksColumns),
        content = {
            item(key = "header", span = { GridItemSpan(maxLineSpan) }) {
                Column(Modifier.fillMaxWidth()) {
                    if (showBack) {
                        IconButton(onClick = backClicked) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = AboutThisAppTheme.strings.accessibilityBack,
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
                    label = it.label,
                    icon = it.icon,
                    tint = it.tint,
                    onClick = it.onClick
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
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                    name = it.dependencyName,
                    author = it.author,
                    url = it.url,
                    icon = DependencyIcon.Icon(icon = R.drawable.ic_util_icon_play, backgroundColor = Color.Red)
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
                    appVersion = appVersion,
                )
            }
        }
    )
}