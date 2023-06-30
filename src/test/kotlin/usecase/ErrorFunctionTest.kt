package usecase

import model.CommandException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import usecase.ErrorFunction.Companion.PROMPT_ERROR
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ErrorFunctionTest {

    lateinit var outputStream: ByteArrayOutputStream
    lateinit var errorFunction: ErrorFunction

    @BeforeEach
    fun setup() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        errorFunction = ErrorFunction()
    }

    @Test
    fun `messages from exception are displayed to the user`() {
        // given
        val message = "error"
        val exception = CommandException.InvalidInputException(message)
        // when
        errorFunction.invoke(exception)
        // then
        outputStream.toString().trim().let {
            assert(it.contains(PROMPT_ERROR) && it.contains(message))
        }
    }

}