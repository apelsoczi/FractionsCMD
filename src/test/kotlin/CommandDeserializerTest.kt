import CommandDeserializer.Companion.REGEX_MIXED_NUMBER
import model.Command.Calculation
import model.Command.Invalid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandDeserializerTest {

    lateinit var deserializer: CommandDeserializer

    @BeforeEach
    fun setUp() {
        deserializer = CommandDeserializer()
    }

    // region: commands

    @Test
    fun `empty string - invalid command`() {
        // given
        val data = ""
        // when
        val command = deserializer.deserialize(data)
        // then
        assert(command is Invalid)
    }

    @Test
    fun `whitespace around operator - invalid command format`() {
        // given
        val data = listOf(
            "1*1",
            "1 *1",
            "1* 1",
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Invalid)
        }
    }

    @Test
    fun `operator input more than one character - invalid format`() {
        // given
        val data = listOf(
            "1 ** 1",
            "1 * * 1",
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Invalid)
        }
    }

    @Test
    fun `operator is not arithmetic - invalid command`() {
        // given
        val data = listOf(
            "1 % 1",
            "1 N 1",
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Invalid)
        }
    }

    @Test
    fun `mixed number format is incorrect - invalid command`() {
        // given
        val data = listOf(
            "1/2/3 + 1",
            "1&&2/3 + 1",
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Invalid)
        }
    }

    // endregion

    @Test
    fun `properly formatted mixed numbers - valid command`() {
        // given
        val data = listOf(
            "1/2 * 3&3/4",
            "2&3/8 + 9/8",
            "1&3/4 - 2",
            "1 / 2",
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Calculation)
        }
    }

    @Test
    fun `regex pattern for mixed number`() {
        // given
        val pattern = "^(-?\\d+)&(-?\\d+)/(-?\\d+)\$|^(-?\\d+)\$|^(-?\\d+)/(-?\\d+)\$"
        // when
        val impl = REGEX_MIXED_NUMBER
        // then
        assert(pattern == impl)
    }

    @Test
    fun `mixed numbers with regex`() {
        // given
        listOf(
            "10&2/5",
            "10&2/-5",
            "10&-2/5",
            "10&-2/-5",
            "-10&2/5",
            "-10&2/-5",
            "-10&-2/5",
            "-10&-2/-5",
            "10",
            "-10",
            "2/5",
            "-2/5",
            "2/-5",
            "-2/-5"
        ).map {
            // when
            it to it.matches(Regex(REGEX_MIXED_NUMBER))
        }.forEach {
            // then
            assert(it.second)
        }
        // and given
        listOf(
            "3/1/1",
            "10%2/5",
            "2\\5",
            "?10&2/5%"
        ).map {
            // and when
            it to it.matches(Regex(REGEX_MIXED_NUMBER))
        }.forEach {
            // and then
            assert(!it.second)
        }
    }

    // region: mixed numbers

    @Test
    fun `deserialize negative positive mixed number whole number numerator denominator`() {
        // given
        val data = "1&2/3 * -1&-2/-3"
        // when
        deserializer.deserialize(data).let {
            // then
            assert(it is Calculation)
            it as Calculation
            assert(it.first.whole == 1 && it.first.numerator == 2 && it.first.denominator == 3)
            assert(it.second.whole == -1 && it.second.numerator == -2 && it.second.denominator == -3)
        }
    }

    @Test
    fun `deserialize whole number and fraction`() {
        // given
        val data = "1 * 2/3"
        // when
        deserializer.deserialize(data).let {
            // then
            assert(it is Calculation)
            it as Calculation
            assert(it.first.whole == 1 && it.first.numerator == 0 && it.first.denominator == 1)
            assert(it.second.whole == 0 && it.second.numerator == 2 && it.second.denominator == 3)
        }
    }

    @Test
    fun `numerator and denominator values are fraction`() {
        // given
        val data = listOf(
            "1/0 * 1",
            "1 * 1/0"
        )
        // when
        data.map {
            deserializer.deserialize(it)
        }.forEach {
            // then
            assert(it is Invalid)
        }
    }

    // endregion

}