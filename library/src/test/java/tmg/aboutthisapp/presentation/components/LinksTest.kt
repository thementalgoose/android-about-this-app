package tmg.aboutthisapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import tmg.aboutthisapp.R

@RunWith(RobolectricTestRunner::class)
internal class LinksTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `link item displays label`() {
        composeTestRule.setContent {
            LinkItem(
                label = R.string.about_this_app_github,
                icon = R.drawable.ic_util_icon_github,
                onClick = { }
            )
        }

        composeTestRule
            .onNodeWithText("Github")
            .assertExists()
    }

    @Test
    fun `link item when clicked calls link clicked`() {
        val onClick: () -> Unit = mockk(relaxed = true)
        composeTestRule.setContent {
            LinkItem(
                label = R.string.about_this_app_github,
                icon = R.drawable.ic_util_icon_github,
                onClick = onClick
            )
        }

        composeTestRule
            .onRoot()
            .performClick()

        verify {
            onClick.invoke()
        }
    }
}