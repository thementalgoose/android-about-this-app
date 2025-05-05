package tmg.aboutthisapp.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.License
import tmg.aboutthisapp.utils.PreviewFoldable
import tmg.aboutthisapp.utils.PreviewPhone
import tmg.aboutthisapp.utils.PreviewTablet

@Composable
fun AboutThisAppScreen(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    dependencyClicked: (Dependency) -> Unit,
    license: List<License>,
    isCompact: Boolean = true,
    showBack: Boolean = true,
    backClicked: () -> Unit = { },
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
    contactEmail: String? = null,
    appVersion: String? = null,
    links: List<Link> = emptyList(),
    linksMaximumColumns: Int = 4,
    contentPadding: PaddingValues = PaddingValues.Absolute()
) {
    if (isCompact) {
        ScreenCompact(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            dependencyClicked = dependencyClicked,
            license = license,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksMaximumColumns = linksMaximumColumns,
            appVersion = appVersion,
            contentPadding = contentPadding,
        )
    } else {
        ScreenExpanded(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            dependencyClicked = dependencyClicked,
            license = license,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksMaximumColumns = linksMaximumColumns,
            appVersion = appVersion,
            contentPadding = contentPadding
        )
    }
}


@PreviewPhone
@Composable
private fun PreviewPhone() {
    AboutThisAppTheme {
        PreviewScreenCompat()
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