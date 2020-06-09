package tmg.components.about


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.about_this_app_activity.*
import tmg.components.R
import tmg.utilities.extensions.setStatusBarColorDark
import tmg.utilities.extensions.views.gone
import tmg.utilities.extensions.views.show
import tmg.utilities.extensions.views.visible
import tmg.utilities.lifecycle.rx.RxActivity

class AboutThisAppActivity : RxActivity(), AboutThisAppDependencyCallback, View.OnClickListener {

    private lateinit var configuration: AboutThisAppConfiguration

    private lateinit var adapter: AboutThisAppDependencyAdapter

    override fun layoutId(): Int {
        return R.layout.about_this_app_activity
    }

    override fun arguments(bundle: Bundle) {
        super.arguments(bundle)
        intent.extras?.let {
            if (it.containsKey(keyConfiguration)) {
                configuration = it.getParcelable(keyConfiguration)!!
            } else {
                configuration = AboutThisAppConfiguration(
                    isDarkMode = it.getBoolean(INTENT_IS_DARK_MODE),
                    github = it.getString(INTENT_GITHUB),
                    email = it.getString(INTENT_EMAIL)!!,
                    website = it.getString(INTENT_WEBSITE),
                    play = it.getString(INTENT_PLAY),
                    appPackageName = it.getString(INTENT_PACKAGE_NAME),
                    dependencies = it.getParcelableArray(INTENT_DEPENDENCIES)
                        ?.map { it as AboutThisAppDependency }
                        ?.toList() ?: listOf(),
                    appName = it.getString(INTENT_APP_NAME)!!,
                    name = it.getString(INTENT_NAME)!!,
                    nameDesc = it.getString(INTENT_NAME_DESC)!!,
                    imageUrl = it.getString(INTENT_IMAGE_URL),
                    imageRes = it.getInt(INTENT_IMAGE_RES),
                    footnote = it.getString(INTENT_FOOTNOTE)!!,
                    thankYou = it.getString(INTENT_THANK_YOU)!!,
                    appVersion = it.getString(INTENT_APP_VERSION)!!
                )
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showButtons(configuration.isDarkMode)

        setupToolbar(R.id.toolbar, true, R.drawable.ic_util_icon_back)
        supportActionBar?.title = ""

        val statusColor = if (configuration.isDarkMode) R.color.aboutThisApp_headerDark else R.color.aboutThisApp_headerLight
        setStatusBarColorDark(
            ContextCompat.getColor(this, statusColor)
        )

        if (configuration.isDarkMode) {
            setDarkTheme()
        } else {
            setLightTheme()
        }

        adapter = AboutThisAppDependencyAdapter(this, configuration.isDarkMode)
        rvAboutThisAppDependencies.layoutManager = LinearLayoutManager(this)
        rvAboutThisAppDependencies.adapter = adapter

        if (configuration.insetsForNavigationBar) {
            ViewCompat.setOnApplyWindowInsetsListener(llAboutThisAppBackground) { view, insets ->
                nsvContent.setPadding(0, 0, 0, insets.systemWindowInsetBottom)

                insets
            }
        }
    }

    private fun showButtons(isDarkMode: Boolean) {
        if (isDarkMode && configuration.github != null) btnGithubLight.visible() else btnGithubLight.gone()
        if (isDarkMode && configuration.website != null) btnWebsiteLight.visible() else btnWebsiteLight.gone()
        if (isDarkMode && configuration.email != null) btnEmailLight.visible() else btnEmailLight.gone()
        if (isDarkMode && (configuration.play != null || configuration.appPackageName != null)) btnPlayLight.visible() else btnPlayLight.gone()
        if (!isDarkMode && configuration.github != null) btnGithubDark.visible() else btnGithubDark.gone()
        if (!isDarkMode && configuration.website != null) btnWebsiteDark.visible() else btnWebsiteDark.gone()
        if (!isDarkMode && configuration.email != null) btnEmailDark.visible() else btnEmailDark.gone()
        if (!isDarkMode && (configuration.play != null || configuration.appPackageName != null)) btnPlayDark.visible() else btnPlayDark.gone()
    }

    override fun observeViewModel() {

        // Inputs

        btnGithubLight.setOnClickListener(this)
        btnEmailLight.setOnClickListener(this)
        btnPlayLight.setOnClickListener(this)
        btnWebsiteLight.setOnClickListener(this)

        btnGithubDark.setOnClickListener(this)
        btnEmailDark.setOnClickListener(this)
        btnPlayDark.setOnClickListener(this)
        btnWebsiteDark.setOnClickListener(this)

        tvAboutThisAppName.text = configuration.name
        tvAboutThisAppDesc.text = configuration.nameDesc

        imgAboutAvatar.show(configuration.imageRes != null || configuration.imageUrl != null)
        Glide.with(this)
            .load(if (configuration.imageUrl != null) configuration.imageUrl else configuration.imageRes)
            .into(imgAboutAvatar)

        tvAboutThisAppAppVersion.text = getString(R.string.about_this_app_app_version, configuration.appVersion)
        tvAboutThisAppThankYou.text = configuration.thankYou
        tvAboutThisAppFootnotes.text = configuration.footnote

        adapter.replaceAll(configuration.dependencies)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDarkTheme() {
        theme(
            background = R.color.aboutThisApp_backgroundDark,
            header = R.color.aboutThisApp_headerDark,
            text = R.color.aboutThisApp_textLight,
            textInverse = R.color.aboutThisApp_textLightSecondary
        )
    }

    private fun setLightTheme() {
        theme(
            background = R.color.aboutThisApp_backgroundLight,
            header = R.color.aboutThisApp_headerLight,
            text = R.color.aboutThisApp_textLight,
            textInverse = R.color.aboutThisApp_textDarkSecondary
        )
    }

    private fun theme(
        @ColorRes background: Int,
        @ColorRes header: Int,
        @ColorRes text: Int,
        @ColorRes textInverse: Int
    ) {
        @ColorInt
        val backgroundColor = ContextCompat.getColor(this, background)
        @ColorInt
        val headerColor = ContextCompat.getColor(this, header)
        @ColorInt
        val textColor = ContextCompat.getColor(this, text)
        @ColorInt
        val textInverseColor = ContextCompat.getColor(this, textInverse)

        llAboutThisAppBackground.setBackgroundColor(backgroundColor)
        toolbar?.setBackgroundColor(headerColor)
        rlToolbar.setBackgroundColor(headerColor)
        tvAboutThisAppName.setTextColor(textColor)
        tvAboutThisAppDesc.setTextColor(textColor)
        tvAboutThisAppThankYou.setTextColor(textInverseColor)
        tvAboutThisAppFootnotes.setTextColor(textInverseColor)
        tvAboutThisAppAppVersion.setTextColor(textInverseColor)
    }

    //region AboutThisAppDependencyCallback

    override fun dependencyItemClicked(item: AboutThisAppDependency) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    //endregion

    override fun onClick(p0: View?) {
        when (p0) {
            btnGithubLight,
            btnGithubDark -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(configuration.github)))
            }
            btnPlayLight,
            btnPlayDark -> {
                val uri = Uri.parse(if (configuration.appPackageName != null) "https://play.google.com/store/apps/details?id=${configuration.appPackageName}" else configuration.play)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            btnWebsiteLight,
            btnWebsiteDark -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(configuration.website)))
            }
            btnEmailLight,
            btnEmailDark -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL, configuration.email)
                intent.putExtra(Intent.EXTRA_SUBJECT, configuration.appName)
                startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_send_email)))
            }
        }
    }

    companion object {

        private const val keyConfiguration: String = "configuration"

        private const val INTENT_NAME: String = "INTENT_NAME"
        private const val INTENT_NAME_DESC: String = "INTENT_NAME_DESC"
        private const val INTENT_IMAGE_URL: String = "INTENT_IMAGE_URL"
        private const val INTENT_IMAGE_RES: String = "INTENT_IMAGE_RES"
        private const val INTENT_GITHUB: String = "INTENT_GITHUB"
        private const val INTENT_EMAIL: String = "INTENT_EMAIL"
        private const val INTENT_WEBSITE: String = "INTENT_WEBSITE"
        private const val INTENT_PLAY: String = "INTENT_PLAY"
        private const val INTENT_PACKAGE_NAME: String = "INTENT_PACKAGE_NAME"
        private const val INTENT_APP_NAME: String = "INTENT_APP_NAME"
        private const val INTENT_APP_VERSION: String = "INTENT_APP_VERSION"
        private const val INTENT_FOOTNOTE: String = "INTENT_FOOTNOTE"
        private const val INTENT_THANK_YOU: String = "INTENT_THANK_YOU"
        private const val INTENT_DEPENDENCIES: String = "INTENT_DEPENDENCIES"
        private const val INTENT_IS_DARK_MODE: String = "INTENT_IS_DARK_MODE"

        @JvmStatic
        fun intent(
            context: Context,
            configuration: AboutThisAppConfiguration
        ): Intent {
            if (configuration.appPackageName != null && configuration.play != null) {
                Log.e(
                    "Components",
                    "You have provided a package name and a play store link. The play store URL will be used"
                )
            }
            val intent = Intent(context, AboutThisAppActivity::class.java)
            intent.putExtra(keyConfiguration, configuration)
            return intent
        }

        @Deprecated(
            "Specifying details individually is discouraged and will be removed",
            ReplaceWith("intent(Context, AboutThisAppConfiguration)")
        )
        @JvmStatic
        fun intent(
            context: Context,
            isDarkMode: Boolean,
            name: String,
            nameDesc: String,
            imageUrl: String? = null,
            @DrawableRes imageRes: Int? = null,
            github: String? = null,
            email: String,
            website: String? = null,
            packageName: String? = null,
            play: String? = null,
            dependencies: List<AboutThisAppDependency>,
            appName: String,
            appVersion: String,
            footnote: String,
            thankYou: String
        ): Intent {
            if (packageName != null && play != null) {
                Log.e(
                    "Components",
                    "You have provided a package name and a play store link. The play store URL will be used"
                )
            }
            val intent = Intent(context, AboutThisAppActivity::class.java)
            intent.putExtra(INTENT_IS_DARK_MODE, isDarkMode)
            intent.putExtra(INTENT_NAME, name)
            intent.putExtra(INTENT_NAME_DESC, nameDesc)
            intent.putExtra(INTENT_IMAGE_URL, imageUrl)
            intent.putExtra(INTENT_IMAGE_RES, imageRes)
            intent.putExtra(INTENT_GITHUB, github)
            intent.putExtra(INTENT_EMAIL, email)
            intent.putExtra(INTENT_WEBSITE, website)
            intent.putExtra(INTENT_PACKAGE_NAME, packageName)
            intent.putExtra(INTENT_PLAY, play)
            intent.putExtra(INTENT_DEPENDENCIES, dependencies.toTypedArray())
            intent.putExtra(INTENT_APP_NAME, appName)
            intent.putExtra(INTENT_APP_VERSION, appVersion)
            intent.putExtra(INTENT_FOOTNOTE, footnote)
            intent.putExtra(INTENT_THANK_YOU, thankYou)
            return intent
        }
    }

}