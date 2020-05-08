package tmg.components.about


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.about_this_app_activity.*
import tmg.components.R
import tmg.utilities.extensions.bindText
import tmg.utilities.extensions.click
import tmg.utilities.extensions.setStatusBarColor
import tmg.utilities.extensions.subscribeNoError
import tmg.utilities.extensions.views.gone
import tmg.utilities.extensions.views.visible
import tmg.utilities.lifecycle.rx.RxActivity

class AboutThisAppActivity: RxActivity(), AboutThisAppDependencyCallback {

    private val viewModel: AboutThisAppVM = ViewModelProvider.NewInstanceFactory().create(AboutThisAppVM::class.java)

    private var isDarkMode: Boolean = false

    private lateinit var name: String
    private lateinit var nameDesc: String
    private lateinit var imageUrl: String
    private var github: String? = null
    private var email: String? = null
    private var website: String? = null
    private var play: String? = null
    private var appPackageName: String? = null
    private lateinit var dependencies: List<AboutThisAppDependency>
    private lateinit var appName: String
    private lateinit var footnote: String
    private lateinit var thankYou: String
    private lateinit var appVersion: String

    private lateinit var adapter: AboutThisAppDependencyAdapter

    override fun layoutId(): Int {
        return R.layout.about_this_app_activity
    }

    override fun arguments(bundle: Bundle) {
        super.arguments(bundle)
        intent.extras?.let {
            isDarkMode = it.getBoolean(INTENT_IS_DARK_MODE)
            github = it.getString(INTENT_GITHUB)
            email = it.getString(INTENT_EMAIL)
            website = it.getString(INTENT_WEBSITE)
            play = it.getString(INTENT_PLAY)
            appPackageName = it.getString(INTENT_PACKAGE_NAME)
            dependencies = it.getParcelableArray(INTENT_DEPENDENCIES)
                ?.map { it as AboutThisAppDependency }
                ?.toList() ?: listOf()
            appName = it.getString(INTENT_APP_NAME)!!
            name = it.getString(INTENT_NAME)!!
            nameDesc = it.getString(INTENT_NAME_DESC)!!
            imageUrl = it.getString(INTENT_IMAGE_URL)!!
            footnote = it.getString(INTENT_FOOTNOTE)!!
            thankYou = it.getString(INTENT_THANK_YOU)!!
            appVersion = it.getString(INTENT_APP_VERSION)!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.inputs.setupLinks(
            github = github,
            website = website,
            email = email,
            play = play,
            appPackage = if (play == null) appPackageName else null
        )
        viewModel.inputs.setupData(
            name = name,
            nameDesc = nameDesc,
            imageUrl = imageUrl,
            footnote = footnote,
            thankYou = thankYou,
            appVersion = appVersion
        )

        viewModel.inputs.setupDependencies(dependencies)

        showButtons(isDarkMode)

        setupToolbar(R.id.toolbar, true, R.drawable.ic_util_icon_back)
        supportActionBar?.title = ""

        setStatusBarColor(ContextCompat.getColor(this, if (isDarkMode) R.color.aboutThisApp_headerDark else R.color.aboutThisApp_headerLight))

        if (isDarkMode) {
            llAboutThisAppBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_backgroundDark))

            toolbar?.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerDark))
            rlToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerDark))
            tvAboutThisAppName.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLight))
            tvAboutThisAppDesc.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))

            tvAboutThisAppThankYou.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
            tvAboutThisAppFootnotes.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
            tvAboutThisAppAppVersion.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))
        }
        else {
            llAboutThisAppBackground.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_backgroundLight))

            toolbar?.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerLight))
            rlToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.aboutThisApp_headerLight))
            tvAboutThisAppName.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLight))
            tvAboutThisAppDesc.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textLightSecondary))

            tvAboutThisAppThankYou.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
            tvAboutThisAppFootnotes.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
            tvAboutThisAppAppVersion.setTextColor(ContextCompat.getColor(this, R.color.aboutThisApp_textDarkSecondary))
        }

        adapter = AboutThisAppDependencyAdapter(this, isDarkMode)
        rvAboutThisAppDependencies.layoutManager = LinearLayoutManager(this)
        rvAboutThisAppDependencies.adapter = adapter
    }

    private fun showButtons(isDarkMode: Boolean) {
        if (isDarkMode && github != null) btnGithubLight.visible() else btnGithubLight.gone()
        if (isDarkMode && website != null) btnWebsiteLight.visible() else btnWebsiteLight.gone()
        if (isDarkMode && email != null) btnEmailLight.visible() else btnEmailLight.gone()
        if (isDarkMode && (play != null || appPackageName != null)) btnPlayLight.visible() else btnPlayLight.gone()
        if (!isDarkMode && github != null) btnGithubDark.visible() else btnGithubDark.gone()
        if (!isDarkMode && website != null) btnWebsiteDark.visible() else btnWebsiteDark.gone()
        if (!isDarkMode && email != null) btnEmailDark.visible() else btnEmailDark.gone()
        if (!isDarkMode && (play != null || appPackageName != null)) btnPlayDark.visible() else btnPlayDark.gone()
    }

    override fun observeViewModel() {

        // Inputs

        btnGithubLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickGithub()
            }
            .autoDispose()

        btnEmailLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickEmail()
            }
            .autoDispose()

        btnPlayLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickPlay()
            }
            .autoDispose()

        btnWebsiteLight
            .click()
            .subscribeNoError {
                viewModel.inputs.clickWebsite()
            }
            .autoDispose()

        btnGithubDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickGithub()
            }
            .autoDispose()

        btnEmailDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickEmail()
            }
            .autoDispose()

        btnPlayDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickPlay()
            }
            .autoDispose()

        btnWebsiteDark
            .click()
            .subscribeNoError {
                viewModel.inputs.clickWebsite()
            }
            .autoDispose()



        // Outputs

        viewModel.outputs
            .clickedWebsite()
            .subscribe {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
            .autoDispose()

        viewModel.outputs
            .clickedEmail()
            .subscribeNoError {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/html"
                intent.putExtra(Intent.EXTRA_EMAIL, it)
                intent.putExtra(Intent.EXTRA_SUBJECT, appName)
                startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_send_email)))
            }
            .autoDispose()

        viewModel.outputs
            .clickedGithub()
            .subscribeNoError {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
            .autoDispose()

        viewModel.outputs
            .clickedPlay()
            .subscribeNoError {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
            .autoDispose()



        viewModel.outputs
            .name()
            .bindText(tvAboutThisAppName)
            .autoDispose()

        viewModel.outputs
            .nameDesc()
            .bindText(tvAboutThisAppDesc)
            .autoDispose()

        viewModel.outputs
            .imageUrl()
            .subscribeNoError {
                Glide.with(this)
                    .load(it)
                    .into(imgAboutAvatar)
            }
            .autoDispose()



        viewModel.outputs
            .appVersion()
            .subscribeNoError {
                tvAboutThisAppAppVersion.text = getString(R.string.app_version_version_name, it)
            }
            .autoDispose()

        viewModel.outputs
            .thankyou()
            .bindText(tvAboutThisAppThankYou)
            .autoDispose()

        viewModel.outputs
            .footnote()
            .bindText(tvAboutThisAppFootnotes)
            .autoDispose()



        viewModel.outputs
            .dependencies()
            .subscribeNoError {
                adapter.replaceAll(it)
            }
            .autoDispose()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    //region AboutThisAppDependencyCallback

    override fun dependencyItemClicked(item: AboutThisAppDependency) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    //endregion

    companion object {

        private const val INTENT_NAME: String = "INTENT_NAME"
        private const val INTENT_NAME_DESC: String = "INTENT_NAME_DESC"
        private const val INTENT_IMAGE_URL: String = "INTENT_IMAGE_URL"
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
        fun intent(context: Context,
                   isDarkMode: Boolean,
                   name: String,
                   nameDesc: String,
                   imageUrl: String,
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
                Log.e("Components", "You have provided a package name and a play store link. The play store URL will be used")
            }
            val intent = Intent(context, AboutThisAppActivity::class.java)
            intent.putExtra(INTENT_IS_DARK_MODE, isDarkMode)
            intent.putExtra(INTENT_NAME, name)
            intent.putExtra(INTENT_NAME_DESC, nameDesc)
            intent.putExtra(INTENT_IMAGE_URL, imageUrl)
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