package tmg.aboutthisapp

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AboutThisAppDependency(
    val order: Int,
    val dependencyName: String,
    val author: String,
    val imageUrl: String = "",
    @DrawableRes
    val imageRes: Int = 0,
    @ColorInt
    val backgroundColor: Int = 0,
    val url: String
): Parcelable