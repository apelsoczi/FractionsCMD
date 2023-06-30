package usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify
import usecase.ShutdownFunction.Companion.PROMPT_SHUTDOWN
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ShutdownFunctionTest {

    private val exitHandler = Mockito.mock<SystemExit>()

    lateinit var outputStream: ByteArrayOutputStream
    lateinit var shutdownFunction: ShutdownFunction

    @BeforeEach
    fun setup() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        shutdownFunction = ShutdownFunction(
            exitHandler = exitHandler
        )
    }

    @Test
    fun `verify exit command from user input`() {
        // when
        shutdownFunction.invoke()
        // then
        outputStream.toString().trim().let {
            assert(it.contains(PROMPT_SHUTDOWN))
        }
        verify(exitHandler).exit()
    }

}