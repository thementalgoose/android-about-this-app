package tmg.aboutthisapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AppVersionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `app version with no version displays nothing`() {
        composeTestRule.setContent {
            AppVersion(
                appVersion = null
            )
        }

        composeTestRule
            .onNodeWithText("App Version")
            .assertDoesNotExist()
    }

    @Test
    fun `app version displays string`() {
        composeTestRule.setContent {
            AppVersion(
                appVersion = "version"
            )
        }

        composeTestRule
            .onNodeWithText("App Version:")
            .assertExists()
        composeTestRule
            .onNodeWithText("version")
            .assertExists()
    }
}