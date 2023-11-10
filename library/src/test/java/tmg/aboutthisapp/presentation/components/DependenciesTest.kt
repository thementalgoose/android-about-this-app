package tmg.aboutthisapp.presentation.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import tmg.aboutthisapp.configuration.DependencyIcon


@RunWith(RobolectricTestRunner::class)
internal class DependenciesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `dependency with shows name, author and url`() {
        composeTestRule.setContent {
            DependencyItem(
                name = "name",
                author = "author",
                url = "url",
                icon = DependencyIcon.Image("")
            )
        }

        composeTestRule
            .onNodeWithText("name")
            .assertExists()
        composeTestRule
            .onNodeWithText("author")
            .assertExists()
        composeTestRule
            .onNodeWithText("url")
            .assertExists()
    }
}