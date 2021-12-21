package tmg.aboutthisapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import tmg.aboutthisapp.databinding.AboutThisAppActivityBinding
import kotlin.reflect.KClass

open class AboutThisAppActivity: AppCompatActivity(),
    AboutThisAppCallback {

    private lateinit var binding: AboutThisAppActivityBinding

    private var _configuration: AboutThisAppConfiguration? = null
    private val configuration: AboutThisAppConfiguration
        get() = _configuration ?: throw RuntimeException("Please provide an 'AboutThisAppConfiguration' item (via. AboutThisAppActivity.intent(context, AboutThisAppConfiguration))")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (_configuration == null) {
            _configuration = intent.extras?.getParcelable(keyConfiguration)
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

        adapter.items = populateList()

        ViewCompat.setOnApplyWindowInsetsListener(binding.aboutThisAppMotionLayout) { _, insets ->
            binding.aboutThisAppInsets.setPadding(0, insets.systemWindowInsetTop, 0, 0)
            binding.aboutThisAppList.setPadding(0, 0, 0, insets.systemWindowInsetBottom)

            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(keyConfiguration, configuration)
        super.onSaveInstanceState(outState)
    }

    private fun populateList(): List<AboutThisAppItem> {
        val list: MutableList<AboutThisAppItem> = mutableListOf()

        list.add(
            AboutThisAppItem.Header(
                play = configuration.playStore,
                email = configuration.email,
                website = configuration.website,
                github = configuration.github
            )
        )

        configuration.subtitle?.let {
            list.add(
                AboutThisAppItem.Message(
                    msg = it
                )
            )
        }

        list.addAll(configuration.dependencies.map {
            AboutThisAppItem.Dependency(it)
        })

        configuration.footnote?.let {
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
                    configuration.appVersion
                )
            )
        )
        configuration.guid?.let {
            list.add(AboutThisAppItem.Message(
                msg = it,
                isPrimary = false,
                isCentered = true,
                longClickCopy = configuration.guidLongClickCopy
            ))
        }
        return list
    }

    //region AboutThisAppDependencyCallback

    override fun dependencyItemClicked(item: AboutThisAppDependency) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    override fun clickPlay() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(configuration.playStore))
        startActivity(intent)
    }

    override fun clickEmail() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, configuration.email)
        intent.putExtra(Intent.EXTRA_SUBJECT, configuration.appName)
        startActivity(Intent.createChooser(intent, getString(R.string.about_this_app_send_email)))
    }

    override fun clickWebsite() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(configuration.website)))
    }

    override fun clickGithub() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(configuration.github)))
    }

    //endregion

    companion object {

        private const val keyConfiguration: String = "configuration"

        @JvmStatic
        fun intent(context: Context, configuration: AboutThisAppConfiguration, clazz: KClass<out AboutThisAppActivity> = AboutThisAppActivity::class): Intent {
            val intent = Intent(context, clazz.java)
            intent.putExtra(keyConfiguration, configuration)
            return intent
        }
    }
}