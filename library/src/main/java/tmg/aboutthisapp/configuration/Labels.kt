package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.R

@Parcelize
data class Labels internal constructor(
    @StringRes
    val contactEmail: Int = R.string.about_this_app_contact_email,
    @StringRes
    val appVersion: Int = R.string.about_this_app_app_version,
    @StringRes
    val accessibilityBack: Int = R.string.about_this_app_ab_back_button,
    @StringRes
    val dependencyHeader: Int = R.string.about_this_app_dependency_header,
    @StringRes
    val licensesHeader: Int = R.string.about_this_app_license_header,
): Parcelable