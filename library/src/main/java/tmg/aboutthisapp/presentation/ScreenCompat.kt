package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.AboutThisAppTheme.dimens.xsmall
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.License
import tmg.aboutthisapp.presentation.components.AppVersion
import tmg.aboutthisapp.presentation.components.CollapsableSection
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
    licenses: List<License>,
    showBack: Boolean = false,
    contactEmail: String? = null,
    links: List<Link> = emptyList(),
    backClicked: () -> Unit = { },
    linksMaximumColumns: Int = 4,
    appVersion: String? = null,
    contentPadding: PaddingValues = PaddingValues.Absolute(),
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
) {
    val expandDependencies = rememberSaveable { mutableStateOf(false) }
    val expandLicenses = rememberSaveable { mutableStateOf(false) }

    LazyVerticalGrid(
        modifier = Modifier
            .background(AboutThisAppTheme.colours.background)
            .testTag("Screen")
            .fillMaxSize(),
        contentPadding = contentPadding,
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

            if (dependencies.isNotEmpty()) {
                item(key = "dependency-header", span = { GridItemSpan(maxLineSpan) }) {
                    CollapsableSection(
                        titleRes = AboutThisAppTheme.strings.dependencyHeader,
                        expanded = expandDependencies.value,
                        modifier = Modifier
                            .clickable {
                                expandDependencies.value = !expandDependencies.value
                            }
                            .padding(
                                vertical = small,
                                horizontal = medium
                            )
                    )
                }
            }
            if (expandDependencies.value) {
                items(dependencies, key = { "${it.dependencyName}-${it.url}"}, span = { GridItemSpan(maxLineSpan) }) {
                    DependencyItem(
                        modifier = Modifier
                            .animateItem()
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
            }

            if (licenses.isNotEmpty()) {
                item(key = "license-header", span = { GridItemSpan(maxLineSpan) }) {
                    CollapsableSection(
                        titleRes = AboutThisAppTheme.strings.licensesHeader,
                        expanded = expandLicenses.value,
                        modifier = Modifier
                            .clickable {
                                expandLicenses.value = !expandLicenses.value
                            }
                            .padding(
                                vertical = small,
                                horizontal = medium
                            )
                    )
                }
            }
            if (expandLicenses.value) {
                items(licenses, key = { "license-${it.label()}"}, span = { GridItemSpan(maxLineSpan) }) {
                    tmg.aboutthisapp.presentation.components.License(
                        modifier = Modifier
                            .animateItem()
                            .padding(
                                horizontal = medium,
                                vertical = small
                            )
                            .clip(RoundedCornerShape(6.dp))
                            .clickable(
                                onClick = {  }
                            ),
                        model = it,
                    )
                }
            }

            if (footer != null) {
                item(key = "content-footer", span = { GridItemSpan(maxLineSpan) }) {
                    Column(Modifier
                        .fillMaxWidth()
                        .padding(top = xsmall)
                    ) {
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