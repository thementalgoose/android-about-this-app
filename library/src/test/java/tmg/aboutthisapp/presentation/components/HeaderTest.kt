package tmg.aboutthisapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import tmg.aboutthisapp.R

@RunWith(RobolectricTestRunner::class)
internal class HeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `header shows app icon, app name and email`() {
        composeTestRule.setContent {
            Header(
                appIcon = R.drawable.ic_util_icon_play,
                appName = "appName",
                contactEmail = "email"
            )
        }

        composeTestRule
            .onNodeWithText("appName")
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription("appName")
            .assertExists()
        composeTestRule
            .onNodeWithText("Contact Email")
            .assertExists()
        composeTestRule
            .onNodeWithText("email")
            .assertExists()
    }

    @Test
    fun `header with no contact email hides label`() {
        composeTestRule.setContent {
            Header(
                appIcon = R.drawable.ic_util_icon_play,
                appName = "appName",
                contactEmail = null
            )
        }

        composeTestRule
            .onNodeWithText("appName")
            .assertExists()
        composeTestRule
            .onNodeWithText("Contact Email")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("email")
            .assertDoesNotExist()
    }

}