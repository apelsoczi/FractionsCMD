import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class NumberSerializerTest {

    lateinit var serializer: NumberSerializer

    @BeforeEach
    fun setup() {
        serializer = NumberSerializer()
    }

    @Test
    fun `deserialize mixed number whole number numerator denominator`() {
        // given
        val data = "1&2/3"
        // when
        val number = serializer.deserialize(data)
        // then
        assertTrue {
            number.whole == 1 && number.numerator == 2 && number.denominator == 3
        }
    }

    @Test
    fun `deserialize whole number`() {
        // given
        val data = "1"
        // when
        val number = serializer.deserialize(data)
        // then
        assertTrue {
            number.whole == 1 && number.numerator == 0 && number.denominator == 0
        }
    }

    @Test
    fun `deserialize fraction numerator denominator`() {
        // given
        val data = "2/3"
        // when
        val number = serializer.deserialize(data)
        // then
        assertTrue {
            number.whole == 0 && number.numerator == 2 && number.denominator == 3
        }
    }

    @Test
    fun `deserialize negatives in mixed number whole number numerator denominator`() {
        // given
        val data = "-1&-2/-3"
        // when
        val number = serializer.deserialize(data)
        // then
        assertTrue {
            number.whole == -1 && number.numerator == -2 && number.denominator == -3
        }
    }

}