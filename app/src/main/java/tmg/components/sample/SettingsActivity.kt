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

        adapter.list = prefsList(applicationContext) {
            category("Title 1") {
                preference(
                    prefsKey = "key_1",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_2",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_3",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_4",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                switch(
                    prefsKey = "key_5",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line",
                    isChecked = false
                )
                preference(
                    prefsKey = "key_6",
                    title = R.string.about_this_app_email,
                    description = R.string.about_this_app_github
                )
                switch(
                    prefsKey = "key_7",
                    title = "Test",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line",
                    isChecked = true
                )
            }
            category("SECOND") {
                preference(
                    prefsKey = "key_testing_1",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_testing_2",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                switch(
                    prefsKey = "key_testing_5",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line",
                    isChecked = true
                )
                switch(
                    prefsKey = "key_testing_7",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line",
                    isChecked = false
                )
                preference(
                    prefsKey = "key_testing_3",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_testing_4",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
                preference(
                    prefsKey = "key_testing_6",
                    title = "Another Test Item",
                    description = "Lorem Ipsum some kind of text here which hopefully spans more than 1 line"
                )
            }
        }
    }
}