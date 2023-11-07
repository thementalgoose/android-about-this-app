package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Dependency(
    val dependencyName: String,
    val author: String,
    val url: String,
    val icon: DependencyIcon
): Parcelable

@Parcelize
sealed class DependencyIcon: Parcelable {
    data class Image(
        val url: String,
    ): DependencyIcon(), Parcelable

    data class Icon(
        val icon: Int,
        @ColorInt
        val backgroundColor: Int
    ): DependencyIcon(), Parcelable
}