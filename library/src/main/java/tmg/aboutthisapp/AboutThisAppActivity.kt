package tmg.aboutthisapp

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.configuration.Configuration
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.presentation.AboutThisAppScreen
import tmg.aboutthisapp.utils.clipboardManager
import java.net.MalformedURLException

class AboutThisAppActivity: AppCompatActivity() {

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
            Log.e("AboutThisApp", "Cannot find configuration whilst creating an activity, closing activity")
            finish()
            return
        }

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this@AboutThisAppActivity)
            AboutThisAppTheme(
                lightColors = config.lightColors?.let { Colours(it) } ?: lightColours,
                darkColors = config.darkColors?.let { Colours(it) } ?: darkColours,
                strings = config.labels
            ) {
                AboutThisAppScreen(
                    appIcon = config.imageRes,
                    appName = config.appName,
                    dependencies = config.dependencies,
                    dependencyClicked = {
                        openLink(it.url)
                    },
                    showBack = true,
                    backClicked = {
                        finish()
                    },
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
            this.add(Link.play { openPlaystore(configuration.appPackageName) })
            if (configuration.email != null) {
                this.add(Link.email { openEmail(configuration.email) })
            }
            if (configuration.github != null) {
                this.add(Link.github { openLink(configuration.github) })
            }
            if (configuration.gitlab != null) {
                this.add(Link.gitlab { openLink(configuration.gitlab) })
            }
            if (configuration.linkedin != null) {
                this.add(Link.linkedIn { openLink(configuration.linkedin) })
            }
            if (configuration.reddit != null) {
                this.add(Link.reddit { openLink(configuration.reddit) })
            }
            if (configuration.twitter != null) {
                this.add(Link.twitter { openLink(configuration.twitter) })
            }
            if (configuration.x != null) {
                this.add(Link.x { openLink(configuration.x) })
            }
            if (configuration.youtube != null) {
                this.add(Link.youtube { openLink(configuration.youtube) })
            }
            if (configuration.website != null) {
                this.add(Link.website { openLink(configuration.website) })
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
                    horizontal = medium,
                    vertical = 12.dp
                )
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun Footer(
        configuration: Configuration,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = medium
            ),
            verticalArrangement = Arrangement.spacedBy(small)
        ) {
            configuration.footnote?.let {
                Text(
                    text = it,
                    color = AboutThisAppTheme.colours.onBackground,
                )
            }
            configuration.debugInfo?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = AboutThisAppTheme.colours.onBackground,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .alpha(0.7f)
                        .combinedClickable(
                            onClick = { },
                            onLongClick = {
                                copyToClipboard(R.string.about_this_app_debug_information, it)
                            }
                        )
                )
            }
        }
    }

    private fun openLink(url: String) {
        val uri = try {
            Uri.parse(url)
        } catch (e: MalformedURLException) {
            return
        }

        val browserSelectorIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.parse("https:"))
        val targetIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(uri)

        targetIntent.selector = browserSelectorIntent
        startActivity(targetIntent)
    }

    private fun openEmail(emailAddress: String) {
        val uri = Uri.parse("mailto:")
        val targetIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_APP_EMAIL)
            .setData(uri)
            .putExtra(Intent.EXTRA_EMAIL, emailAddress)
        startActivity(Intent.createChooser(targetIntent, getString(R.string.about_this_app_email)))
    }

    private fun openPlaystore(appPackageName: String) {
        try {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            ))
        } catch (e : ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

    private fun copyToClipboard(@StringRes label: Int, content: String) {
        val newClipData = ClipData.newPlainText(
            getString(label),
            content
        )
        clipboardManager?.setPrimaryClip(newClipData)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(applicationContext, R.string.about_this_app_copy_to_clipboard, Toast.LENGTH_LONG).show()
        }
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