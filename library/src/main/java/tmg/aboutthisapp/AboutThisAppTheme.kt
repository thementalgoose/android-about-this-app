package tmg.aboutthisapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import tmg.aboutthisapp.configuration.AboutThisAppStrings

internal val LocalColors = staticCompositionLocalOf { lightColours }
internal val LocalStrings = staticCompositionLocalOf { AboutThisAppStrings.blank() }

internal object AboutThisAppTheme {
    val colours
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val strings
        @Composable
        @ReadOnlyComposable
        get() = LocalStrings.current
}


@Composable
internal fun AboutThisAppTheme(
    lightColors: AboutThisAppPallette = lightColours,
    darkColors: AboutThisAppPallette = darkColours,
    strings: AboutThisAppStrings = AboutThisAppStrings.default(),
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