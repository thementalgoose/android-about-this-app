package tmg.components.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tmg.components.about.AboutThisAppConfiguration
import tmg.components.about.AboutThisAppDependency
import tmg.components.about.AboutThisAppActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAboutThisApp.setOnClickListener {

            val configuration = AboutThisAppConfiguration(
                themeRes = R.style.AppTheme,
                appName = "Sample App",
                appVersion = "1.0.0",
                dependencies = List(14) {
                    AboutThisAppDependency(
                        order = it,
                        dependencyName = "Utilities",
                        author = "Jordan Fisher",
                        imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                        url = "https://github.com/thementalgoose/android-utilities"
                    )
                },
                email = "thementalgoose@gmail.com",
                footnote = "Thank you!",
                github = "https://github.com/thementalgoose/android-components",
                play = null,
                name = "Flashback",
                nameDesc = "Formula 1 Statistics",
                imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                appPackageName = this.packageName,
                subtitle = "Thank you!",
                website = "https://jordanfisher.io"
            )

            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        configuration = configuration
                    )
            )
        }

        btnAboutThisAppDarkMode.setOnClickListener {

            val configuration = AboutThisAppConfiguration(
                themeRes = R.style.AppThemeDark,
                appName = "Sample App",
                appVersion = "1.0.0",
                dependencies = List(15) {
                    AboutThisAppDependency(
                        order = it,
                        dependencyName = "Utilities",
                        author = "Jordan Fisher",
                        imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                        url = "https://github.com/thementalgoose/android-utilities"
                    )
                },
                email = "thementalgoose@gmail.com",
                footnote = "Thank you!",
                github = "https://github.com/thementalgoose/android-components",
                play = null,
                name = "Flashback",
                nameDesc = "Formula 1 Statistics",
                imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                imageBackground = R.drawable.test_image_background,
                appPackageName = this.packageName,
                subtitle = "Thanks again!",
                website = "https://jordanfisher.io"
            )

            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        configuration = configuration
                    )
            )
        }

        btnAboutThisAppDarkImageRes.setOnClickListener {

            val configuration = AboutThisAppConfiguration(
                themeRes = R.style.AppTheme,
                appName = "Sample App",
                appVersion = "1.0.0",
                dependencies = List(5) {
                    AboutThisAppDependency(
                        order = it,
                        dependencyName = "Utilities",
                        author = "Jordan Fisher",
                        imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                        url = "https://github.com/thementalgoose/android-utilities"
                    )
                },
                email = "thementalgoose@gmail.com",
                footnote = "Thank you!",
                github = "https://github.com/thementalgoose/android-components",
                play = null,
                name = "Jordan Fisher",
                nameDesc = "App developer!",
                imageRes = R.drawable.ic_launcher_foreground,
                imageBackground = R.drawable.test_image_background,
                appPackageName = this.packageName,
                subtitle = "Thanks again!",
                website = "https://jordanfisher.io"
            )

            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        configuration = configuration
                    )
            )
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}