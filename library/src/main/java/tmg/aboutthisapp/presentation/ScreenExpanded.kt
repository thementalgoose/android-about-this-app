package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.presentation.components.AppVersion
import tmg.aboutthisapp.presentation.components.DependencyItem
import tmg.aboutthisapp.presentation.components.Header
import tmg.aboutthisapp.presentation.components.LinkItem

private val minDependencyCellSize = 220.dp

@Composable
internal fun ScreenExpanded(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    dependencyClicked: (Dependency) -> Unit,
    showBack: Boolean = false,
    backClicked: () -> Unit = { },
    contactEmail: String? = null,
    links: List<Link> = emptyList(),
    linksMaximumColumns: Int = 4,
    appVersion: String? = null,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
) {
    Row(modifier = Modifier
        .background(AboutThisAppTheme.colours.background)
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .width(350.dp),
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
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(
                    end = medium,
                    top = medium,
                    bottom = medium
                )
                .clip(RoundedCornerShape(medium))
                .background(AboutThisAppTheme.colours.surface)
            ) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(
                        horizontal = medium
                    ),
                    columns = StaggeredGridCells.Adaptive(minSize = minDependencyCellSize),
                    content = {
                        item(key = "header", span = StaggeredGridItemSpan.FullLine) {
                            Text(
                                modifier = Modifier.padding(vertical = medium),
                                text = stringResource(AboutThisAppTheme.strings.dependencyHeader),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AboutThisAppTheme.colours.onSurface
                            )
                        }
                        items(dependencies, key = { "${it.dependencyName}${it.url}" }) {
                            DependencyItem(
                                modifier = Modifier
                                    .padding(
                                        horizontal = small,
                                        vertical = small
                                    )
                                    .clickable { dependencyClicked(it) },
                                name = it.dependencyName,
                                author = it.author,
                                url = it.url,
                                icon = it.icon
                            )
                        }
                    }
                )
            }
        }
    }
}