package tmg.aboutthisapp

import androidx.compose.ui.graphics.Color


internal val lightColours = AboutThisAppPallette(
    colorPrimary = Color(0xFF6EF9F5),
    background = Color(0xFFFFFBFC),
    surface = Color(0xFFEFEBEC),
    primary = Color(0xFFDFDBDC),
    onBackground = Color(0xFF181818),
    onSurface = Color(0xFF282828),
    onPrimary = Color(0xFF202020)
)

internal val darkColours = AboutThisAppPallette(
    colorPrimary = Color(0xFF07B0AB),
    background = Color(0xFF181818),
    surface = Color(0xFF282828),
    primary = Color(0xFF202020),
    onBackground = Color(0xFFFFFBFC),
    onSurface = Color(0xFFEFEBEC),
    onPrimary = Color(0xFFDFDBDC),
)

internal data class AboutThisAppPallette(
    val colorPrimary: Color,
    val background: Color,
    val surface: Color,
    val primary: Color,
    val onPrimary: Color,
    val onSurface: Color,
    val onBackground: Color
)