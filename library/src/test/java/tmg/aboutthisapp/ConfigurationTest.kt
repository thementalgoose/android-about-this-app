package tmg.aboutthisapp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import tmg.aboutthisapp.configuration.Configuration
import java.lang.RuntimeException

internal class ConfigurationTest {

    @Test
    fun `about this app initialisation throws runtime exception is both play or package name is null`() {

        assertThrows<RuntimeException> {
            getAboutThisAppConfig(play = null, appPackageName = null)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "play.google.com,,play.google.com",
        ",com.package,https://play.google.com/store/apps/details?id=com.package",
        "play.google.com,com.package,play.google.com"
    )
    fun `about this app initialisates and returns expected play store link`(play: String?, appPackageName: String?, expectedPlayStore: String) {

        val config = getAboutThisAppConfig(play, appPackageName)
        assertEquals(expectedPlayStore, config.playStore)
    }

    private fun getAboutThisAppConfig(
        play: String? = "https://play.google.com",
        appPackageName: String? = "package.name"
    ) = Configuration(
        themeRes = R.style.ThemeOverlay_AppCompat,
        name = "test",
        nameDesc = "desc",
        appPackageName = appPackageName,
        play = play,
        appName = "my app",
        appVersion = "1.0.0",
        dependencies = emptyList()
    )
}