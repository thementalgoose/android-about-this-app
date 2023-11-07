package tmg.aboutthisapp.presentation

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.DependencyIcon
import tmg.aboutthisapp.configuration.Link
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
    isCompact: Boolean = true,
    showBack: Boolean = true,
    backClicked: () -> Unit = { },
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
    contactEmail: String? = null,
    appVersion: String? = null,
    links: List<Link> = emptyList(),
    linksMaximumColumns: Int = 4,
) {
    if (isCompact) {
        ScreenCompact(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            dependencyClicked = dependencyClicked,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksMaximumColumns = linksMaximumColumns,
            appVersion = appVersion
        )
    } else {
        ScreenExpanded(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            dependencyClicked = dependencyClicked,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksMaximumColumns = linksMaximumColumns,
            appVersion = appVersion
        )
    }
}


@PreviewPhone
@Composable
private fun PreviewPhone() {
    AboutThisAppTheme {
        BoxWithConstraints {
            AboutThisAppScreen(
                isCompact = maxWidth <= 600.dp,
                appIcon = R.drawable.ic_util_icon_play,
                appName = "My Application",
                contactEmail = "thementalgoose@gmail.com",
                dependencyClicked = { },
                header = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        color = AboutThisAppTheme.colours.onBackground,
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mattis maximus nisi ac mollis. Vivamus fringilla mi vulputate turpis bibendum congue. Proin ut consectetur nisl, non tempor risus. Phasellus venenatis lacinia dignissim. Pellentesque pretium, elit quis condimentum egestas, ligula ante venenatis ex, sed faucibus tellus quam at purus"
                    )
                },
                footer = {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        color = AboutThisAppTheme.colours.onBackground,
                        text = "Special thanks to everyone here. If we're missing anyone or anything please let us know"
                    )
                },
                appVersion = "1.0.1234",
                dependencies = dependencyList(3),
                links = links()
            )
        }
    }
}

@PreviewTablet
@PreviewFoldable
@Composable
private fun PreviewExpanded() {
    AboutThisAppTheme {
        AboutThisAppScreen(
            isCompact = false,
            appIcon = R.drawable.ic_util_icon_play,
            appName = "My Application",
            contactEmail = "thementalgoose@gmail.com",
            header = {
                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mattis maximus nisi ac mollis. Vivamus fringilla mi vulputate turpis bibendum congue. Proin ut consectetur nisl, non tempor risus. Phasellus venenatis lacinia dignissim. Pellentesque pretium, elit quis condimentum egestas, ligula ante venenatis ex, sed faucibus tellus quam at purus"
                )
            },
            footer = {
                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Special thanks to everyone here. If we're missing anyone or anything please let us know"
                )
            },
            appVersion = "1.0.1234",
            dependencies = dependencyList(10),
            dependencyClicked = { },
            links = links()
        )
    }
}

private fun links() = listOf(
    Link.github {  },
    Link.website {  },
    Link.play {  },
    Link.email {  },
    Link.gitlab {  },
    Link.linkedIn {  },
    Link.x {  },
    Link.twitter {  },
    Link.youtube {  },
    Link.reddit {  },
)

private fun dependencyList(count: Int) = List(count) {
    fakeDependency(dependencyName = "dependency $it")
}
private fun fakeDependency(
    dependencyName: String
) = Dependency(
    dependencyName = dependencyName,
    author = "Author",
    url = "http://www.url.com",
    icon = DependencyIcon.Icon(
        icon = R.drawable.ic_util_icon_website,
        backgroundColor = Color.BLUE
    ),
)
