import model.toImproperFraction
import model.toMixedNumber
import org.apache.commons.math3.fraction.Fraction
import org.junit.jupiter.api.Test


class MixedNumberTest {

    @Test
    fun `mixed number to improper fraction`() {
        // given
        listOf(
            "10" to Fraction(10),
            "-10" to Fraction(-10),
            "2/5" to Fraction(2, 5),
            "2/-5" to Fraction(-2, 5),
            "-2/5" to Fraction(-2, 5),
            "-2/-5" to Fraction(2, 5),
            "10&2/5" to Fraction(52, 5),
            "10&2/-5" to Fraction(-48, 5),
            "10&-2/5" to Fraction(48, 5),
            "10&-2/-5" to Fraction(-52, 5),
            "-10&2/5" to Fraction(-52, 5),
            "-10&2/-5" to Fraction(48, 5),
            "-10&-2/5" to Fraction(-48, 5),
            "-10&-2/-5" to Fraction(52, 5),
        ).map {
            // when
            it.first.toMixedNumber().toImproperFraction() to it.second
        }.forEach {
            // then
            assert(it.first == it.second)
        }
    }

}