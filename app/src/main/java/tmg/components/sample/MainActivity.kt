package tmg.components.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tmg.components.about.AboutThisAppActivity
import tmg.components.about.AboutThisAppDependency

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAboutThisApp.setOnClickListener {
            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        isDarkMode = false,
                        appName = "Sample App",
                        appVersion = "1.0.0",
                        dependencies = List(4) {
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
                        imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                        packageName = this.packageName,
                        thankYou = "Thank you!",
                        website = "https://jordanfisher.io"
                    )
            )
        }

        btnAboutThisAppDarkMode.setOnClickListener {
            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        isDarkMode = true,
                        appName = "Sample App",
                        appVersion = "1.0.0",
                        dependencies = List(4) {
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
                        imageUrl = "https://avatars0.githubusercontent.com/u/5982159?s=460&v=4",
                        packageName = this.packageName,
                        thankYou = "Thank you!",
                        website = "https://jordanfisher.io"
                    )
            )
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}