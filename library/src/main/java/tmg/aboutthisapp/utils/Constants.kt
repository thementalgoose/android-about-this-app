package tmg.aboutthisapp.utils

private const val marketUri: String = "https://play.google.com/store/apps/details?id=%s"

fun getMarketUri(appPackage: String): String = marketUri.format(appPackage)