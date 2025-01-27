package tmg.aboutthisapp

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import tmg.aboutthisapp.configuration.AboutThisAppColors
import tmg.aboutthisapp.configuration.AboutThisAppTypography
import tmg.aboutthisapp.configuration.Labels
import tmg.aboutthisapp.configuration.aboutThisAppDarkColors
import tmg.aboutthisapp.configuration.aboutThisAppLightColors
import tmg.aboutthisapp.configuration.defaultTypography

internal val LocalColors = staticCompositionLocalOf { aboutThisAppLightColors }
internal val LocalTypography = staticCompositionLocalOf { defaultTypography }
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
    lightColors: AboutThisAppColors = aboutThisAppLightColors,
    darkColors: AboutThisAppColors = aboutThisAppDarkColors,
    typography: AboutThisAppTypography = defaultTypography,
    strings: Labels = Labels(),
    content: @Composable () -> Unit
) {
    val darkMode = isSystemInDarkTheme()
    val colors = if (darkMode) darkColors else lightColors
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalStrings provides strings,
        LocalTypography provides typography,
        LocalIndication provides ripple()
    ) {
        content()
    }
}
