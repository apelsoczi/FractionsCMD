package usecase

import CommandDeserializer
import model.Command

class ProcessInputFunction(
    private val commandDeserializer: CommandDeserializer,
) {

    fun invoke(): Command {
        println(LINE_DIVIDER)
        println(INPUT_PROMPT)
        val command = commandDeserializer.deserialize(
            data = readLine() ?: ""
        )
        return command
    }

    companion object {
        val LINE_DIVIDER = "---------------------------------"
        val INPUT_PROMPT = "Enter two fractions to calculate:"
    }
}