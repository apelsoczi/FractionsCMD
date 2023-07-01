import model.toImproperFraction
import model.toMixedNumber
import org.junit.jupiter.api.Test


class MixedNumberTest {

    @Test
    fun `mixed number to big decimal`() {
        // given
        listOf(
            "10&2/5" to "10.4",
            "10&2/-5" to "-9.6",
            "10&-2/5" to "9.6",
            "10&-2/-5" to "-10.4",
            "-10&2/5" to "-10.4",
            "-10&2/-5" to "10.4",
            "-10&-2/5" to "-9.6",
            "-10&-2/-5" to "9.6",
        ).map {
            // when
            it.first.toMixedNumber().toImproperFraction().decimal.toString() to it.second
        }.forEach {
            // then
            assert(it.first == it.second)
        }
    }

}