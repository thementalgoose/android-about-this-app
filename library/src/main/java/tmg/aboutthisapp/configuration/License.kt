package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
sealed class OpenSourceLicenses: Parcelable {
    data object PlayServicesOpenSource: OpenSourceLicenses(), Parcelable
    data class Manual(
        val licenses: List<License>
    ): OpenSourceLicenses(), Parcelable
}

@Keep
@Parcelize
sealed class License: Parcelable {
    data class Url(
        val label: String,
        val url: String
    ): License(), Parcelable
    data class Text(
        val label: String,
        val text: String
    ): License(), Parcelable

    fun label(): String {
        return when (this) {
            is Text -> this.label
            is Url -> this.label
        }
    }
}