package tmg.components.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tmg.components.prefs.*
import tmg.components.sample.databinding.ActivitySettingsBinding

class SettingsActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    lateinit var adapter: AppPreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AppPreferencesAdapter(
            prefClicked = { key ->
                Toast.makeText(applicationContext, key, Toast.LENGTH_LONG).show()
            },
            prefSwitchClicked = { key, isChecked ->
                Toast.makeText(applicationContext, "$key is $isChecked", Toast.LENGTH_LONG).show()
            }
        )

        binding.rvSettings.layoutManager = LinearLayoutManager(this)
        binding.rvSettings.adapter = adapter

        adapter.list = prefsList {
            category(R.string.settings_cat_1) {
                preference(
                    prefsKey = "key_1",
                    title = R.string.settings_pref_title,
                    description = R.string.settings_pref_desc
                )
                switch(
                    prefsKey = "key_7",
                    title = R.string.settings_switchpref_title,
                    description = R.string.settings_switchpref_title,
                    isChecked = true
                )
            }
            category(R.string.settings_cat_1) {
                preference(
                    prefsKey = "key_2",
                    title = R.string.settings_pref_title,
                    description = R.string.settings_pref_desc
                )
                switch(
                    prefsKey = "key_3",
                    title = R.string.settings_switchpref_title,
                    description = R.string.settings_switchpref_desc,
                    isChecked = false
                )
            }
            footer(BuildConfig.VERSION_NAME)
        }
    }
}