@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package tmg.aboutthisapp

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.BundleCompat
import tmg.aboutthisapp.AboutThisAppTheme.dimens.medium
import tmg.aboutthisapp.AboutThisAppTheme.dimens.small
import tmg.aboutthisapp.AboutThisAppTheme.dimens.xsmall
import tmg.aboutthisapp.configuration.AboutThisAppColors
import tmg.aboutthisapp.configuration.AboutThisAppTypography
import tmg.aboutthisapp.configuration.Configuration
import tmg.aboutthisapp.configuration.Link
import tmg.aboutthisapp.configuration.aboutThisAppDarkColors
import tmg.aboutthisapp.configuration.aboutThisAppLightColors
import tmg.aboutthisapp.configuration.defaultTypography
import tmg.aboutthisapp.presentation.AboutThisAppScreen
import tmg.aboutthisapp.utils.clipboardManager
import java.net.MalformedURLException

class AboutThisAppActivity: ComponentActivity() {

    private val configuration: Configuration? by lazy {
        return@lazy intent.extras?.let {
            BundleCompat.getParcelable(it, keyConfiguration, Configuration::class.java)
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = configuration ?: run {
            Log.e("AboutThisApp", "Cannot find configuration whilst creating an activity, closing activity")
            finish()
            return
        }

        this.enableEdgeToEdge(
            statusBarStyle = when (config.setIsDarkMode) {
                true -> SystemBarStyle.dark(Color.TRANSPARENT)
                false -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                null -> SystemBarStyle.auto(
                    lightScrim = Color.WHITE,
                    darkScrim = Color.BLACK
                )
            },
            navigationBarStyle = when (config.setIsDarkMode) {
                true -> SystemBarStyle.dark(Color.TRANSPARENT)
                false -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
                null -> SystemBarStyle.auto(
                    lightScrim = Color.WHITE,
                    darkScrim = Color.BLACK
                )
            },
        )

        val lightColors = config.lightColors?.let { AboutThisAppColors(it) } ?: aboutThisAppLightColors
        val darkColors = config.darkColors?.let { AboutThisAppColors(it) } ?: aboutThisAppDarkColors
        val typography = config.typography?.let { AboutThisAppTypography(it) } ?: defaultTypography

        val licenses = when (config.license) {
            is OpenSourceLicenses.Manual -> config.license.licenses
            OpenSourceLicenses.PlayServicesOpenSource -> PlayServiceLicenseUtils.readLicenses(this)
        }
        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this@AboutThisAppActivity)
            AboutThisAppTheme(
                lightColors = lightColors,
                darkColors = darkColors,
                darkMode = config.setIsDarkMode ?: isSystemInDarkTheme(),
                typography = typography,
                strings = config.labels
            ) {
                Scaffold {
                    Box(Modifier
                        .background(AboutThisAppTheme.colours.background)
                        .fillMaxSize()
                        .padding(it)
                    ) {
                        AboutThisAppScreen(
                            appIcon = config.imageRes,
                            appName = config.appName,
                            dependencies = config.dependencies,
                            dependencyClicked = {
                                openLink(it.url)
                            },
                            license = licenses,
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
                    vertical = medium
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
        Column(modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(small)
        ) {
            configuration.footnote?.let {
                Text(
                    modifier = Modifier
                        .padding(
                            vertical = small,
                            horizontal = medium
                        ),
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
                        .alpha(0.7f)
                        .padding(
                            horizontal = small,
                            vertical = xsmall
                        )
                        .clip(RoundedCornerShape(6.dp))
                        .combinedClickable(
                            onClick = { },
                            onLongClick = {
                                copyToClipboard(R.string.about_this_app_debug_information, it)
                            }
                        )
                        .padding(
                            horizontal = small,
                            vertical = xsmall
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
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, emailAddress)
                putExtra(Intent.EXTRA_SUBJECT, "")
            }
            startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_email)))
        } catch (e: ActivityNotFoundException) {
            copyToClipboard(R.string.about_this_app_email, emailAddress)
        }
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