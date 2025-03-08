package tmg.aboutthisapp.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    showBackground = true,
    name = "Phone Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_4
)
@Preview(
    showBackground = true,
    name = "Phone Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Preview(
    showBackground = true,
    name = "Phone Landscape Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    widthDp = 891,
    heightDp = 411,
    device = Devices.PIXEL_4
)
@Preview(
    showBackground = true,
    name = "Phone Landscape Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 891,
    heightDp = 411,
    device = Devices.PIXEL_4
)
annotation class PreviewPhone


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=1200dp,height=800dp,dpi=480",
    name = "Tablet Dark"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=1200dp,height=800dp,dpi=480",
    name = "Tablet Light"
)
annotation class PreviewTablet


@Preview(
    showBackground = true,
    backgroundColor = 0xff181818,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:width=673dp,height=841dp,dpi=480",
    name = "Foldable Dark"
)
@Preview(
    showBackground = true,
    backgroundColor = 0xfff8f8f8,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=673dp,height=841dp,dpi=480",
    name = "Foldable Light"
)
annotation class PreviewFoldable


@Preview(
    showBackground = true,
    backgroundColor = 0xff181818,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    backgroundColor = 0xfff8f8f8,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light"
)
annotation class PreviewTheme