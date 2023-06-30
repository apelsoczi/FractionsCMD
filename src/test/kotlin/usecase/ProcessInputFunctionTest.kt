package usecase

import CommandDeserializer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify
import usecase.ProcessInputFunction.Companion.INPUT_PROMPT
import usecase.ProcessInputFunction.Companion.LINE_DIVIDER
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class ProcessInputFunctionTest {

    private val commandDeserializer = Mockito.mock<CommandDeserializer>()

    lateinit var outputStream: ByteArrayOutputStream
    lateinit var inputStream: ByteArrayInputStream
    lateinit var processInputFunction: ProcessInputFunction

    @BeforeEach
    fun setup() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        processInputFunction = ProcessInputFunction(
            commandDeserializer = commandDeserializer
        )
    }

    @Test
    fun `prompt user to receive calculator input`() {
        // given
        val input = "2 * 2"
        inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
        // when
        processInputFunction.invoke()
        // then
        outputStream.toString().trim().let {
            assert(it.contains(LINE_DIVIDER) && it.contains(INPUT_PROMPT))
        }
        verify(commandDeserializer).deserialize(input)
    }

}