package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.AboutThisAppConfigColors

@Keep
@Parcelize
data class Configuration(
    @DrawableRes
    val imageRes: Int,
    val appName: String,
    val appVersion: String,
    val appPackageName: String,
    val dependencies: List<Dependency>,
    val header: String? = null,
    val footnote: String? = null,
    val github: String? = null,
    val email: String? = null,
    val website: String? = null,
    val debugInfo: String? = null,
    val lightColors: AboutThisAppConfigColors? = null,
    val darkColors: AboutThisAppConfigColors? = null,
    val strings: AboutThisAppStrings = AboutThisAppStrings()
): Parcelable