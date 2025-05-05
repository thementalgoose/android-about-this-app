package tmg.aboutthisapp.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.AboutThisAppTheme.dimens.large
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.DependencyIcon
import tmg.aboutthisapp.configuration.License
import tmg.aboutthisapp.configuration.Link

internal fun fakeLinks() = listOf(
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

internal fun fakeLicenses(count: Int) = List(count) {
    fakeLicense(name = "license $it")
}
internal fun fakeLicense(
    name: String
) = License.Url(
    label = name,
    url = "https://www.testurl.com"
)

internal fun fakeDependencyList(count: Int) = List(count) {
    fakeDependency(dependencyName = "dependency $it")
}
internal fun fakeDependency(
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

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun PreviewScreenCompat() {
    BoxWithConstraints {
        AboutThisAppScreen(
            isCompact = maxWidth <= 600.dp,
            appIcon = R.drawable.ic_util_icon_play,
            appName = "My Application",
            contactEmail = "thementalgoose@gmail.com",
            dependencyClicked = { },
            header = {
                Text(
                    modifier = Modifier.padding(large),
                    fontSize = 14.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mattis maximus nisi ac mollis. Vivamus fringilla mi vulputate turpis bibendum congue. Proin ut consectetur nisl, non tempor risus. Phasellus venenatis lacinia dignissim. Pellentesque pretium, elit quis condimentum egestas, ligula ante venenatis ex, sed faucibus tellus quam at purus"
                )
            },
            footer = {
                Text(
                    modifier = Modifier.padding(large),
                    fontSize = 14.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Special thanks to everyone here. If we're missing anyone or anything please let us know"
                )
            },
            appVersion = "1.0.1234",
            dependencies = fakeDependencyList(1),
            license = fakeLicenses(1),
            links = fakeLinks()
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun PreviewScreenExpanded() {
    AboutThisAppTheme {
        ScreenExpanded(
            appIcon = R.drawable.ic_util_icon_play,
            appName = "My Application",
            contactEmail = "thementalgoose@gmail.com",
            header = {
                Text(
                    modifier = Modifier.padding(large),
                    fontSize = 14.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Lorem ipsum dolor sit amet"
                )
            },
            footer = {
                Text(
                    modifier = Modifier.padding(large),
                    fontSize = 14.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    text = "Special thanks to everyone here. If we're missing anyone or anything please let us know"
                )
            },
            appVersion = "1.0.1234",
            dependencies = fakeDependencyList(10),
            dependencyClicked = { },
            license = fakeLicenses(10),
            links = fakeLinks(),
        )
    }
}