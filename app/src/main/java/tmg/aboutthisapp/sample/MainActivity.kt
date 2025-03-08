package tmg.aboutthisapp.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.AboutThisAppActivity
import tmg.aboutthisapp.AboutThisAppTheme
import tmg.aboutthisapp.configuration.Configuration
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.DependencyIcon

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AboutThisAppTheme {
                Scaffold(
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Link(
                                label = "About this app (system)",
                                configurationToOpen = sampleConfiguration.copy(setIsDarkMode = null)
                            )
                            Link(
                                label = "About this app (light mode)",
                                configurationToOpen = sampleConfiguration.copy(setIsDarkMode = false)
                            )
                            Link(
                                label = "About this app (dark mode)",
                                configurationToOpen = sampleConfiguration.copy(setIsDarkMode = true)
                            )
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun Link(
        label: String,
        configurationToOpen: Configuration
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                launchAboutThisAppActivity(configurationToOpen)
            }
        ) {
            Text(
                text = label,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    val sampleConfiguration: Configuration
        get() = Configuration(
            appName = "Sample App",
            appVersion = "1.0.0",
            dependencies = List(14) {
                Dependency(
                    dependencyName = "Dep ${it + 1}",
                    author = "Albert Einstein",
                    icon = DependencyIcon.Image("https://avatars0.githubusercontent.com/u/5982159?s=460&v=4"),
                    url = "https://github.com/thementalgoose/android-about-this-app"
                )
            },
            email = "thementalgoose@gmail.com",
            header = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mattis maximus nisi ac mollis. Vivamus fringilla mi vulputate turpis bibendum congue. Proin ut consectetur nisl, non tempor risus. Phasellus venenatis lacinia dignissim. Pellentesque pretium, elit quis condimentum egestas, ligula ante venenatis ex, sed faucibus tellus quam at purus",
            footnote = "Thank you!",
            github = "https://github.com/thementalgoose/android-components",
            x = "https://www.google.com",
            twitter = "https://www.google.com",
            reddit = "https://www.google.com",
            linkedin = "https://www.google.com",
            youtube = "https://www.google.com",
            gitlab = "https://www.google.com",
            debugInfo = "abcdefg-abcd-abcd-abcdefg",
            appPackageName = this.packageName,
            website = "https://jordanfisher.io",
            imageRes = R.drawable.ic_launcher_background,
        )

    private fun launchAboutThisAppActivity(configuration: Configuration) {
        startActivity(
            AboutThisAppActivity
                .intent(
                    context = this,
                    configuration = configuration
                )
        )
    }
}