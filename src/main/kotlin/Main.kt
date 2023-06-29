import usecase.CalculationFunction
import usecase.ErrorFunction
import usecase.ProcessInputFunction
import usecase.ShutdownFunction

fun main(args: Array<String>) {
    val calculator = Calculator(
        numberSerializer = NumberSerializer(),
        processInputFunction = ProcessInputFunction(
            commandDeserializer = CommandDeserializer(),
        ),
        calculationFunction = CalculationFunction(),
        errorFunction = ErrorFunction(),
        shutdownFunction = ShutdownFunction(),
    )
    calculator.process()
}


