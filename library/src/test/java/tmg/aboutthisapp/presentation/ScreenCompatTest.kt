package tmg.aboutthisapp.presentation

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import tmg.aboutthisapp.R
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.configuration.DependencyIcon
import tmg.aboutthisapp.configuration.Link

@RunWith(RobolectricTestRunner::class)
internal class ScreenCompatTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dependencyClicked: (Dependency) -> Unit = mockk(relaxed = true)
    private val linkClicked: () -> Unit = mockk(relaxed = true)
    private val backClicked: () -> Unit = mockk(relaxed = true)

    private fun initUnderTest(
        showBack: Boolean = true,
        dependencies: List<Dependency> = listOf(
            Dependency(
                dependencyName = "dependencyName",
                author = "author",
                url = "url",
                icon = DependencyIcon.Image("")
            ),
            Dependency(
                dependencyName = "dependencyName2",
                author = "author2",
                url = "url2",
                icon = DependencyIcon.Image("")
            )
        ),
        contactEmail: String? = "email",
        links: List<Link> = listOf(Link.x(linkClicked))
    ) {
        composeTestRule.setContent {
            ScreenCompact(
                appIcon = R.drawable.ic_util_icon_play,
                appName = "appName",
                dependencies = dependencies,
                dependencyClicked = dependencyClicked,
                showBack = showBack,
                contactEmail = contactEmail,
                links = links,
                backClicked = backClicked,
                appVersion = "appVersion",
                header = {
                    Text("Header")
                },
                footer = {
                    Text("Footer")
                }
            )
        }
    }
    @Test
    fun `screen content renders everything`() {
        initUnderTest()

        composeTestRule
            .onNodeWithContentDescription("Back")
            .assertExists()

        composeTestRule
            .onNodeWithText("appName")
            .assertExists()
        composeTestRule
            .onNodeWithText("email")
            .assertExists()
        composeTestRule
            .onNodeWithText("X")
            .assertExists()
        composeTestRule
            .onNodeWithText("Header")
            .assertExists()

        composeTestRule
            .onNodeWithTag("Screen")
            .performScrollToIndex(2)

        composeTestRule
            .onNodeWithText("dependencyName")
            .assertExists()
        composeTestRule
            .onNodeWithText("dependencyName2")
            .assertExists()

        composeTestRule
            .onNodeWithText("Footer")
            .assertExists()
        composeTestRule
            .onNodeWithText("App Version:")
            .assertExists()
    }

    @Test
    fun `content with no dependencies still shows footer`() {
        initUnderTest(
            dependencies = emptyList()
        )

        composeTestRule
            .onNodeWithText("Footer")
            .assertExists()
        composeTestRule
            .onNodeWithText("App Version:")
            .assertExists()
    }

    @Test
    fun `click link fires callback`() {
        initUnderTest()

        composeTestRule
            .onNodeWithText("X")
            .assertExists()
            .performClick()

        verify {
            linkClicked.invoke()
        }
    }

    @Test
    fun `click dependency fires callback`() {
        initUnderTest()

        composeTestRule
            .onNodeWithText("dependencyName")
            .assertExists()
            .performClick()

        verify {
            dependencyClicked.invoke(any())
        }
    }

    @Test
    fun `back button is hidden when back is false`() {
        initUnderTest(
            showBack = false
        )

        composeTestRule
            .onNodeWithContentDescription("Back")
            .assertDoesNotExist()
    }

    @Test
    fun `click back fires back`() {
        initUnderTest(
            showBack = true
        )

        composeTestRule
            .onNodeWithContentDescription("Back")
            .assertExists()
            .performClick()

        verify {
            backClicked.invoke()
        }
    }
}