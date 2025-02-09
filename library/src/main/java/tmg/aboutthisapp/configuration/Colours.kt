package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class Colours(
    @ColorInt
    val colorPrimary: Int,
    @ColorInt
    val background: Int,
    @ColorInt
    val surface: Int,
    @ColorInt
    val primary: Int,
    @ColorInt
    val onBackground: Int,
    @ColorInt
    val onSurface: Int,
    @ColorInt
    val onPrimary: Int,
): Parcelable


internal val aboutThisAppLightColors = AboutThisAppColors(
    colorPrimary = Color(0xFF6EF9F5),
    background = Color(0xFFFFFBFC),
    surface = Color(0xFFEFEBEC),
    primary = Color(0xFFDFDBDC),
    onBackground = Color(0xFF181818),
    onSurface = Color(0xFF282828),
    onPrimary = Color(0xFF202020)
)

internal val aboutThisAppDarkColors = AboutThisAppColors(
    colorPrimary = Color(0xFF07B0AB),
    background = Color(0xFF181818),
    surface = Color(0xFF282828),
    primary = Color(0xFF202020),
    onBackground = Color(0xFFFFFBFC),
    onSurface = Color(0xFFEFEBEC),
    onPrimary = Color(0xFFDFDBDC),
)

data class AboutThisAppColors(
    val colorPrimary: Color,
    val background: Color,
    val surface: Color,
    val primary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val onPrimary: Color,
) {
    constructor(
        config: Colours
    ): this(
        colorPrimary = Color(config.colorPrimary),
        background = Color(config.background),
        surface = Color(config.surface),
        primary = Color(config.primary),
        onBackground = Color(config.onBackground),
        onSurface = Color(config.onSurface),
        onPrimary = Color(config.onPrimary)
    )
}