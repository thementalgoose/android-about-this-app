package tmg.aboutthisapp.configuration

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize
import tmg.aboutthisapp.R

@Parcelize
data class AboutThisAppStrings internal constructor(
    @StringRes
    val contactEmail: Int = R.string.about_this_app_contact_email,
    @StringRes
    val appVersion: Int = R.string.about_this_app_app_version,
    @StringRes
    val accessibilityBack: Int = R.string.about_this_app_ab_back_button,
    @StringRes
    val dependencyHeader: Int = R.string.about_this_app_dependency_header,
): Parcelable