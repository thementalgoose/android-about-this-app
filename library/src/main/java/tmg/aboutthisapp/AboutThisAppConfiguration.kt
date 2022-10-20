package tmg.aboutthisapp

import android.os.Parcelable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.Keep
import androidx.annotation.StyleRes
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.utils.getMarketUri

@Keep
@Parcelize
data class AboutThisAppConfiguration(
    @StyleRes
    val themeRes: Int,
    val name: String,
    val nameDesc: String,
    val imageUrl: String? = null,
    @DrawableRes
    val imageRes: Int? = null,
    @DrawableRes
    val imageBackground: Int? = null,
    val github: String? = null,
    val email: String? = null,
    val website: String? = null,
    private val appPackageName: String? = null,
    private val play: String? = null,
    val appName: String,
    val appVersion: String,
    val subtitle: String? = null,
    val footnote: String? = null,
    val guid: String? = null,
    val guidLongClickCopy: Boolean = false,
    var dependencies: List<AboutThisAppDependency>
): Parcelable {

    init {
        if (play == null && appPackageName == null) {
            throw RuntimeException("Please provide either an appPackageName or a play store URL")
        }
        else if (play != null && appPackageName != null) {
            Log.e("Components", "You have provided a package name and a play store link. The play store URL will be used")
        }
    }

    val playStore: String
        get() {
            return when {
                play != null -> {
                    play
                }
                appPackageName != null -> {
                    getMarketUri(appPackageName)
                }
                else -> {
                    throw RuntimeException("Please provide either an appPackageName or a play store URL")
                }
            }
        }
}