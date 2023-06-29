import Command.*
import kotlin.system.exitProcess

/** The main class to execute the application */
class Calculator(
    private val commandDeserializer: CommandDeserializer,
) {

    /** reset the calculator for command to be entered by user */
    fun clearInput() {
        println("---------------------------------")
        println("Enter two fractions to calculate:")
        val command = commandDeserializer.deserialize(
            data = readLine() ?: ""
        )
        executeProgram(command)
    }

    private fun executeProgram(command: Command) {
        when (command) {
            is Clear -> {
                clearInput()
            }
            is Calculation -> {
                println("${command.firstFraction} ${command.operation} ${command.secondFraction}")
                clearInput()
            }
            is Invalid -> {
                println("Error: ${command.exception.message}")
                clearInput()
            }
            is ExitProcess -> {
                println("... shutting down")
                exitProcess(0)
            }
        }
    }

}