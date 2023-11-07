package tmg.aboutthisapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tmg.aboutthisapp.configuration.Configuration
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.AboutThisAppStrings
import tmg.aboutthisapp.presentation.AboutThisAppScreen

open class AboutThisAppActivity: AppCompatActivity() {

    open fun onConfigurationNotFound() {
        Log.e("AboutThisApp", "Cannot find configuration whilst creating an activity, closing activity")
        finish()
    }

    private val configuration: Configuration? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(keyConfiguration, Configuration::class.java)
        } else {
            intent.extras?.getParcelable(keyConfiguration)
        }
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = configuration ?: run {
            onConfigurationNotFound()
            return
        }

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this@AboutThisAppActivity)
            AboutThisAppTheme {
                AboutThisAppScreen(
                    appIcon = config.imageRes,
                    appName = config.appName,
                    dependencies = config.dependencies,
                    isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact,
                    header = {
                        Header(configuration = config)
                    },
                    footer = {
                        Footer(configuration = config)
                    },
                    contactEmail = config.email,
                    appVersion = config.appVersion,
                    links = buildLinks(config),
                )
            }
        }
    }

    private fun buildLinks(
        configuration: Configuration
    ): List<Link> = mutableListOf<Link>()
        .apply {
            this.add(Link.Play { openPlaystore(configuration.playStore) })
            if (configuration.github != null) {
                this.add(Link.Github { openLink(configuration.github) })
            }
            if (configuration.website != null) {
                this.add(Link.Website { openLink(configuration.website) })
            }
            if (configuration.email != null) {
                this.add(Link.Email { openEmail(configuration.email) })
            }
        }
        .toList()

    @Composable
    private fun Header(
        configuration: Configuration
    ) {
        if (configuration.header != null) {
            Text(
                text = configuration.header,
                color = AboutThisAppTheme.colours.onBackground,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
            )
        }
    }

    @Composable
    private fun Footer(
        configuration: Configuration,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            configuration.footnote?.let {
                Text(
                    text = it,
                    color = AboutThisAppTheme.colours.onBackground,
                )
            }
            configuration.guid?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.alpha(0.7f)
                )
            }
        }
    }

    private fun openLink(url: String) {

    }

    private fun openEmail(emailAddress: String) {

    }

    private fun openPlaystore(playstore: String) {

    }

    companion object {

        @Keep
        private const val keyConfiguration: String = "configuration"

        @JvmStatic
        @Keep
        fun intent(context: Context, configuration: Configuration?): Intent {
            val intent = Intent(context, AboutThisAppActivity::class.java)
            intent.putExtra(keyConfiguration, configuration)
            return intent
        }
    }
}