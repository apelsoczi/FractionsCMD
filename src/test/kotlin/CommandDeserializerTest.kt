import model.Command
import model.Command.Invalid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandDeserializerTest {

    lateinit var deserializer: CommandDeserializer

    @BeforeEach
    fun setUp() {
        deserializer = CommandDeserializer()
    }

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
    fun `properly formatted fractions - valid command`() {
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
            assert(it is Command)
        }
    }

}