package tmg.aboutthisapp.presentation

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.AboutThisAppStrings
import tmg.aboutthisapp.utils.PreviewFoldable
import tmg.aboutthisapp.utils.PreviewPhone
import tmg.aboutthisapp.utils.PreviewTablet

private val iconSize: Dp = 108.dp
private val edgePadding: Dp = 16.dp

@Composable
fun AboutThisAppScreen(
    @DrawableRes
    appIcon: Int,
    appName: String,
    dependencies: List<Dependency>,
    isCompact: Boolean = true,
    showBack: Boolean = true,
    backClicked: () -> Unit = { },
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
    contactEmail: String? = null,
    appVersion: String? = null,
    links: List<Link> = emptyList(),
    linksColumns: Int = 4,
) {
    if (isCompact) {
        ScreenCompact(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksColumns = linksColumns,
            appVersion = appVersion
        )
    } else {
        ScreenExpanded(
            appIcon = appIcon,
            appName = appName,
            showBack = showBack,
            backClicked = backClicked,
            dependencies = dependencies,
            header = header,
            footer = footer,
            contactEmail = contactEmail,
            links = links,
            linksColumns = linksColumns,
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
            links = links()
        )
    }
}

private fun links() = listOf(
    Link.Github {  },
    Link.Website {  },
    Link.Play {  },
    Link.Email {  },
)

private fun dependencyList(count: Int) = List(count) {
    fakeDependency(dependencyName = "dependency $it")
}
private fun fakeDependency(
    dependencyName: String
) = Dependency(
    order = 1,
    dependencyName = dependencyName,
    author = "Author",
    imageUrl = "imageUrl",
    imageRes = R.drawable.ic_util_icon_website,
    backgroundColor = Color.BLUE,
    url = "http://www.url.com",
)
