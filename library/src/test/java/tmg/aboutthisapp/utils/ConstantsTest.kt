package tmg.aboutthisapp.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ConstantsTest {

    @ParameterizedTest(name = "get market uri for {0} returns correct market uri of {1}")
    @CsvSource(
        "com.test,https://play.google.com/store/apps/details?id=com.test",
        "com.another.package,https://play.google.com/store/apps/details?id=com.another.package",
    )
    fun `get market uri returns correct market uri`(appPackage: String, expectedMarketUri: String) {
        assertEquals(expectedMarketUri, getMarketUri(appPackage))
    }
}