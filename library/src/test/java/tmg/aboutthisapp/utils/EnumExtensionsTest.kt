package tmg.aboutthisapp.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EnumExtensionsTest {

    enum class SampleIntEnum(
        val key: Int
    ){
        A(21),
        B(19);
    }

    @Test
    fun `Int toEnum enum standard parsing is correct`() {
        assertEquals(SampleIntEnum.A, 0.toEnum<SampleIntEnum>())
        assertEquals(SampleIntEnum.B, 1.toEnum<SampleIntEnum>())
        assertNull(4.toEnum<SampleIntEnum>())
    }


    @Test
    fun `Int toEnum custom enum parsing is correct`() {
        assertEquals(SampleIntEnum.A, 21.toEnum<SampleIntEnum> { it.key })
        assertEquals(SampleIntEnum.B, 19.toEnum<SampleIntEnum> { it.key })
        assertNull(20.toEnum<SampleIntEnum> { it.key })
    }

    enum class SampleStringEnum(
        val key: String
    ){
        A("z"),
        B("y");
    }

    @Test
    fun `String toEnum enum standard parsing is correct`() {

        assertEquals(SampleStringEnum.A, "A".toEnum<SampleStringEnum>())
        assertEquals(SampleStringEnum.B, "B".toEnum<SampleStringEnum>())
        assertNull("UNSUPPORTED".toEnum<SampleStringEnum>())
    }


    @Test
    fun `String toEnum custom enum parsing is correct`() {

        assertEquals(SampleStringEnum.A, "z".toEnum<SampleStringEnum> { it.key })
        assertEquals(SampleStringEnum.B, "y".toEnum<SampleStringEnum> { it.key })
        assertNull("UNSUPPORTED".toEnum<SampleStringEnum> { it.key })
    }

}