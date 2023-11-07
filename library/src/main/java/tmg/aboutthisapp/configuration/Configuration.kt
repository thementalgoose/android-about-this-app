package tmg.aboutthisapp.configuration

import android.os.Parcelable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StyleRes
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.configuration.Dependency
import tmg.aboutthisapp.utils.getMarketUri

@Keep
@Parcelize
data class Configuration(
    val name: String,
    @DrawableRes
    val imageRes: Int,
    val appName: String,
    val appVersion: String,
    val playStore: String,
    val dependencies: List<Dependency>,
    val subtitle: String? = null,
    val header: String? = null,
    val footnote: String? = null,
    val github: String? = null,
    val email: String? = null,
    val website: String? = null,
    val guid: String? = null,
): Parcelable {

    @JvmOverloads
    constructor(
        name: String,
        imageRes: Int,
        appName: String,
        appVersion: String,
        dependencies: List<Dependency>,
        appPackageName: String,
        website: String? = null,
        subtitle: String? = null,
        github: String? = null,
        email: String? = null,
        header: String? = null,
        footnote: String? = null,
        guid: String? = null,
    ): this(
        name = name,
        imageRes = imageRes,
        github = github,
        email = email,
        website = website,
        appName = appName,
        playStore = getMarketUri(appPackageName),
        appVersion = appVersion,
        subtitle = subtitle,
        header = header,
        footnote = footnote,
        guid = guid,
        dependencies = dependencies,
    )
}