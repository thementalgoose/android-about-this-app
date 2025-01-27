package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.parcelize.Parcelize

internal val defaultTypography: AboutThisAppTypography = AboutThisAppTypography(
    header = TextStyle(
        fontSize = 24.sp
    ),
    title = TextStyle(
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontSize = 12.sp
    )
)

@Parcelize
data class Typography(
    val headerFontSize: Int,
    val titleFontSize: Int,
    val body1FontSize: Int,
    val body2FontSize: Int
): Parcelable

data class AboutThisAppTypography(
    val header: TextStyle,
    val title: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
) {
    constructor(
        typography: Typography
    ) : this(
        header = TextStyle(
            fontSize = typography.headerFontSize.sp
        ),
        title = TextStyle(
            fontSize = typography.titleFontSize.sp
        ),
        body1 = TextStyle(
            fontSize = typography.body1FontSize.sp
        ),
        body2 = TextStyle(
            fontSize = typography.body2FontSize.sp
        )
    )
}