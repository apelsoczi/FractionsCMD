import model.Command
import model.Command.*
import usecase.CalculationFunction
import usecase.ErrorFunction
import usecase.ProcessInputFunction
import usecase.ShutdownFunction

/** The main class to execute the application */
class Calculator(
    private val numberSerializer: NumberSerializer,
    private val processInputFunction: ProcessInputFunction,
    private val calculationFunction: CalculationFunction,
    private val errorFunction: ErrorFunction,
    private val shutdownFunction: ShutdownFunction,
) {

    /** reset the calculator for command to be entered by user */
    fun process() {
        executeProgram(
            command = processInputFunction.invoke()
        )
    }

    private fun executeProgram(command: Command) {
        when (command) {
            is Clear -> {
                process()
            }
            is Calculation -> {
                val first = numberSerializer.deserialize(command.firstFraction)
                val second = numberSerializer.deserialize(command.secondFraction)
                when {
                    command.operation == "+" -> calculationFunction.add(first, second)
                    command.operation == "-" -> calculationFunction.subtract(first, second)
                    command.operation == "*" -> calculationFunction.multiply(first, second)
                    command.operation == "/" -> calculationFunction.divide(first, second)
                }
                process()
            }
            is Invalid -> {
                errorFunction.invoke(command.exception)
                process()
            }
            is ExitProcess -> {
                shutdownFunction.invoke()
            }
        }
    }

}