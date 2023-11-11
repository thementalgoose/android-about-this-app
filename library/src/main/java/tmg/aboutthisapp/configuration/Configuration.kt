package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.ConfigurationColours

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
    val gitlab: String? = null,
    val linkedin: String? = null,
    val youtube: String? = null,
    val twitter: String? = null,
    val x: String? = null,
    val reddit: String? = null,
    val email: String? = null,
    val website: String? = null,
    val debugInfo: String? = null,
    val lightColors: ConfigurationColours? = null,
    val darkColors: ConfigurationColours? = null,
    val labels: Labels = Labels()
): Parcelable