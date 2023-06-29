package usecase

import CommandDeserializer
import model.Command

class ProcessInputFunction(
    private val commandDeserializer: CommandDeserializer,
) {

    fun invoke(): Command {
        println("---------------------------------")
        println("Enter two fractions to calculate:")
        val command = commandDeserializer.deserialize(
            data = readLine() ?: ""
        )
        return command
    }

}