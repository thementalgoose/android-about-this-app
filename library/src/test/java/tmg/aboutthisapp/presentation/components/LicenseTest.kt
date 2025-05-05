package tmg.aboutthisapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import tmg.aboutthisapp.configuration.License

@RunWith(RobolectricTestRunner::class)
internal class LicenseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `dependency with shows name, author and url`() {
        composeTestRule.setContent {
            License(
                model = License.Url(label = "label", url = "https://www.google.com")
            )
        }

        composeTestRule
            .onNodeWithText("label")
            .assertExists()
    }
}