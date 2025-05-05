@file:OptIn(ExperimentalMaterial3Api::class)

package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small_medium
import tmg.aboutthisapp.LocalTypography
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.License
import tmg.aboutthisapp.presentation.components.AppVersion
import tmg.aboutthisapp.presentation.components.CollapsableSection
import tmg.aboutthisapp.presentation.components.DependencyItem
import tmg.aboutthisapp.presentation.components.Header
import tmg.aboutthisapp.presentation.components.LinkItem
import tmg.aboutthisapp.utils.PreviewFoldable
import tmg.aboutthisapp.utils.PreviewTablet

private val minDependencyCellSize = 220.dp

@Composable
internal fun ScreenExpanded(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    dependencyClicked: (Dependency) -> Unit,
    licenses: List<License>,
    showBack: Boolean = false,
    backClicked: () -> Unit = { },
    contactEmail: String? = null,
    links: List<Link> = emptyList(),
    linksMaximumColumns: Int = 4,
    appVersion: String? = null,
    contentPadding: PaddingValues = PaddingValues.Absolute(),
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
) {
    val expandDependencies = rememberSaveable { mutableStateOf(true) }
    val expandLicenses = rememberSaveable { mutableStateOf(true) }

    Row(modifier = Modifier
        .background(AboutThisAppTheme.colours.background)
        .fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            columns = GridCells.Fixed(links.size.coerceIn(1, linksMaximumColumns)),
            contentPadding = contentPadding,
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
                        tint = it.tint,
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
                        modifier = Modifier.padding(
                            horizontal = medium,
                            vertical = medium,
                        ),
                        appVersion = appVersion,
                    )
                }
            }
        )

        if (dependencies.isNotEmpty() || licenses.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(
                        end = medium,
                        top = medium,
                        bottom = medium
                    )
                    .clip(RoundedCornerShape(medium))
                    .background(AboutThisAppTheme.colours.surface)
                ) {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier,
                        columns = StaggeredGridCells.Adaptive(minSize = minDependencyCellSize),
                        contentPadding = PaddingValues.Absolute(left = small, right = small),
                        content = {
                            if (dependencies.isNotEmpty()) {
                                item(key = "header-dependency", span = StaggeredGridItemSpan.FullLine) {
                                    CollapsableSection(
                                        titleRes = AboutThisAppTheme.strings.dependencyHeader,
                                        expanded = expandDependencies.value,
                                        modifier = Modifier
                                            .clickable {
                                                expandDependencies.value = !expandDependencies.value
                                            }
                                            .fillMaxWidth()
                                            .padding(horizontal = medium, vertical = small_medium)
                                    )
                                }
                            }
                            if (expandDependencies.value) {
                                items(dependencies, key = { "${it.dependencyName}${it.url}" }) {
                                    DependencyItem(
                                        modifier = Modifier
                                            .animateItem()
                                            .padding(
                                                horizontal = small,
                                                vertical = small
                                            )
                                            .clip(RoundedCornerShape(6.dp))
                                            .clickable(
                                                onClick = { dependencyClicked(it) }
                                            ),
                                        name = it.dependencyName,
                                        author = it.author,
                                        url = it.url,
                                        icon = it.icon
                                    )
                                }
                            }
                            if (licenses.isNotEmpty()) {
                                item(key = "header-licenses", span = StaggeredGridItemSpan.FullLine) {
                                    CollapsableSection(
                                        titleRes = AboutThisAppTheme.strings.licensesHeader,
                                        expanded = expandLicenses.value,
                                        modifier = Modifier
                                            .clickable {
                                                expandLicenses.value = !expandLicenses.value
                                            }
                                            .fillMaxWidth()
                                            .padding(horizontal = medium, vertical = small_medium)
                                    )
                                }
                            }
                            if (expandLicenses.value) {
                                items(licenses, key = { "licenses-${it.label()}" }) {
                                    tmg.aboutthisapp.presentation.components.License(
                                        modifier = Modifier
                                            .animateItem()
                                            .padding(
                                                horizontal = small,
                                                vertical = small
                                            )
                                            .clip(RoundedCornerShape(6.dp)),
                                        model = it
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}



@PreviewTablet
@PreviewFoldable
@Composable
private fun PreviewExpanded() {
    AboutThisAppTheme {
        PreviewScreenExpanded()
    }
}