package tmg.aboutthisapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.configuration.Labels

internal val LocalColors = staticCompositionLocalOf { lightColours }
internal val LocalStrings = staticCompositionLocalOf { Labels() }

internal object AboutThisAppTheme {
    val colours
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val strings
        @Composable
        @ReadOnlyComposable
        get() = LocalStrings.current

    @Suppress("ClassName")
    object dimens {
        val large = 24.dp
        val medium = 16.dp
        val small = 8.dp
        val xsmall = 4.dp
    }
}


@Composable
fun AboutThisAppTheme(
    lightColors: Colours = lightColours,
    darkColors: Colours = darkColours,
    strings: Labels = Labels(),
    content: @Composable () -> Unit
) {
    val darkMode = isSystemInDarkTheme()
    CompositionLocalProvider(
        LocalColors provides if (darkMode) darkColors else lightColors,
        LocalStrings provides strings
    ) {
        content()
    }
}
