package tmg.components.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tmg.components.about.AboutThisAppConfiguration
import tmg.components.about.AboutThisAppDependency
import tmg.components.about.AboutThisAppActivity
import tmg.components.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAboutThisApp.setOnClickListener {

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

        binding.btnAboutThisAppDarkMode.setOnClickListener {

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

        binding.btnAboutThisAppDarkImageRes.setOnClickListener {

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

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}