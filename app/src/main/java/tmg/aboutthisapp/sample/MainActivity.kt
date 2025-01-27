package tmg.aboutthisapp.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tmg.aboutthisapp.AboutThisAppActivity
import tmg.aboutthisapp.configuration.Configuration
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.DependencyIcon
import tmg.aboutthisapp.configuration.Typography
import tmg.aboutthisapp.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAboutThisApp.setOnClickListener {

            val configuration = Configuration(
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

            startActivity(
                AboutThisAppActivity
                    .intent(
                        context = this,
                        configuration = configuration
                    )
            )
        }
    }
}