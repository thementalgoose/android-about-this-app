package tmg.aboutthisapp.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import tmg.aboutthisapp.R

data class AboutThisAppStrings internal constructor(
    val contactEmail: String,
    val appVersion: String,
    val accessibilityBack: String,
    val dependencyHeader: String,
) {
    companion object {
        @Composable
        @ReadOnlyComposable
        fun default(
            contactEmail: String = stringResource(R.string.about_this_app_contact_email),
            appVersion: String = stringResource(R.string.about_this_app_app_version),
            accessibilityBack: String = stringResource(R.string.about_this_app_ab_back_button),
            dependencyHeader: String = stringResource(id = R.string.about_this_app_dependency_header)
        ) = AboutThisAppStrings(
            contactEmail = contactEmail,
            appVersion = appVersion,
            accessibilityBack = accessibilityBack,
            dependencyHeader = dependencyHeader
        )

        internal fun blank() = AboutThisAppStrings(
            contactEmail = "",
            appVersion = "",
            accessibilityBack = "",
            dependencyHeader = "",
        )
    }
}