import Command.*
import CommandException.*

/** deserialize calculator input into a [Command] */
class CommandDeserializer {

    /** deserialize input from system.in to a [Command] for program execution */
    fun deserialize(data: String): Command {
        val data = data.trim()

        val exception = when {
            data.isEmpty() -> InvalidInputException("Input is empty.")
            data.count { it == ' ' } != 2 -> InvalidWhitespaceException("Format input as '<fraction> <+,-,/,*> <fraction>'")
            else -> null
        }
        if (exception != null) return Invalid(exception)
        if (data.lowercase() == "exit") return ExitProcess

        // operator
        val indexOfOperatorStart = data.indexOfFirst { it == ' ' } + 1
        val indexOfOperatorEnd = data.indexOfLast { it == ' ' }
        val operator = data.substring(
            startIndex = indexOfOperatorStart,
            endIndex = indexOfOperatorEnd,
        ).trim()
        if (operator.length != 1) return Invalid(OperatorFormattingException("Too many operators '$operator'."))
        val validOperators = listOf('/', '*', '-', '+')
        if (operator[0] !in validOperators) return Invalid(OperatorFormattingException("Not an operator ( +, -, *, / )"))

        // fractions
        val firstFraction = data.substring(
            startIndex = 0,
            endIndex = indexOfOperatorStart,
        )
        val secondFraction = data.substring(
            startIndex = indexOfOperatorEnd+1,
            endIndex = data.length,
        )

        return Calculation(
            firstFraction = firstFraction,
            operation = operator,
            secondFraction = secondFraction,
        )
    }
}

