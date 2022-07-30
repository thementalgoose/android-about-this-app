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
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import tmg.aboutthisapp.databinding.AboutThisAppActivityBinding
import tmg.aboutthisapp.utils.clipboardManager
import kotlin.reflect.KClass

open class AboutThisAppActivity: AppCompatActivity(), AboutThisAppCallback {

    private lateinit var binding: AboutThisAppActivityBinding

    open fun onConfigurationNotFound() {
        Log.e("AboutThisApp", "Cannot find configuration whilst creating an activity, closing activity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration: AboutThisAppConfiguration = intent.extras?.getParcelable<AboutThisAppConfiguration>(keyConfiguration) ?: run {
            onConfigurationNotFound()
            finish()
            return
        }
        setTheme(configuration.themeRes)

        binding = AboutThisAppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aboutThisAppBackButton.setOnClickListener {
            onBackPressed()
        }

        configuration.imageBackground?.let {
            binding.aboutThisAppIcon.setBackgroundResource(it)
        }

        if (configuration.imageUrl != null) {
            Glide.with(this)
                .load(configuration.imageUrl)
                .into(binding.aboutThisAppIcon)
        }
        else if (configuration.imageRes != null) {
            Glide.with(this)
                .load(configuration.imageRes)
                .into(binding.aboutThisAppIcon)
        }

        binding.aboutThisAppName.text = configuration.name
        binding.aboutThisAppNameToolbar.text = configuration.name
        binding.aboutThisAppNameDesc.text = configuration.nameDesc

        val adapter = AboutThisAppAdapter(this)
        binding.aboutThisAppList.adapter = adapter
        binding.aboutThisAppList.layoutManager = LinearLayoutManager(this)

        adapter.items = configuration.populateList()

        ViewCompat.setOnApplyWindowInsetsListener(binding.aboutThisAppMotionLayout) { _, insets ->
            binding.aboutThisAppInsets.setPadding(0, insets.systemWindowInsetTop, 0, 0)
            binding.aboutThisAppList.setPadding(0, 0, 0, insets.systemWindowInsetBottom)

            insets
        }
    }

    private fun AboutThisAppConfiguration.populateList(): List<AboutThisAppItem> {
        val list: MutableList<AboutThisAppItem> = mutableListOf()

        list.add(
            AboutThisAppItem.Header(
                play = this.playStore,
                email = this.email,
                website = this.website,
                github = this.github,
                appName = this.appName
            )
        )

        this.subtitle?.let {
            list.add(
                AboutThisAppItem.Message(
                    msg = it
                )
            )
        }

        list.addAll(this.dependencies.map {
            AboutThisAppItem.Dependency(it)
        })

        this.footnote?.let {
            list.add(
                AboutThisAppItem.Message(
                    msg = it,
                    isCentered = false
                )
            )
        }

        list.add(
            AboutThisAppItem.Message(
                getString(
                    R.string.about_this_app_app_version,
                    this.appVersion
                )
            )
        )
        this.guid?.let {
            list.add(AboutThisAppItem.Message(
                msg = it,
                isPrimary = false,
                isCentered = true,
                longClickCopy = this.guidLongClickCopy
            ))
        }
        return list
    }

    //region AboutThisAppDependencyCallback

    override fun dependencyItemClicked(item: AboutThisAppDependency) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    override fun clickUrl(url: String?) {
        val webUrl = url ?: return
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val clipData = ClipData.newPlainText("", url)
            this.clipboardManager?.setPrimaryClip(clipData)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                Toast.makeText(this, R.string.about_this_app_copy_to_clipboard, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun sendEmail(email: String?, subject: String) {
        val emailAddress = email ?: return
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)

        try {
            startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_send_email)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.about_this_app_unable_to_find_client), Toast.LENGTH_LONG).show()
        }
    }

    //endregion

    companion object {

        @Keep
        private const val keyConfiguration: String = "configuration"

        @JvmStatic
        @Keep
        fun intent(context: Context, configuration: AboutThisAppConfiguration?, clazz: KClass<out AboutThisAppActivity> = AboutThisAppActivity::class): Intent {
            val intent = Intent(context, clazz.java)
            intent.putExtra(keyConfiguration, configuration)
            return intent
        }
    }
}