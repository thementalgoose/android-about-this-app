package tmg.components.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_settings.*
import tmg.components.prefs.*

class SettingsActivity: AppCompatActivity() {

    lateinit var adapter: AppPreferencesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        adapter = AppPreferencesAdapter(
            prefClicked = { key ->
                Toast.makeText(applicationContext, key, Toast.LENGTH_LONG).show()
            },
            prefSwitchClicked = { key, isChecked ->
                Toast.makeText(applicationContext, "$key is $isChecked", Toast.LENGTH_LONG).show()
            }
        )

        rvSettings.layoutManager = LinearLayoutManager(this)
        rvSettings.adapter = adapter

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
                    description = R.string.settings_switchpref_title,
                    isChecked = false
                )
            }
            footer(BuildConfig.VERSION_NAME)
        }
    }
}