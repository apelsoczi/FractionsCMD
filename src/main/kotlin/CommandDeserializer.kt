import model.Command
import model.Command.*
import model.CommandException.*
import model.toMixedNumber

/** deserialize calculator input into a [Command] */
class CommandDeserializer {

    /** deserialize input from system.in to a [Command] for program execution */
    fun deserialize(data: String): Command {
        val data = data.trim()
        if (data.lowercase() == "exit") return ExitProcess

        val exception = when {
            data.isEmpty() -> InvalidInputException("Input is empty.")
            data.count { it == ' ' } != 2 -> InvalidWhitespaceException("Format input as '<fraction> <+,-,/,*> <fraction>'")
            else -> null
        }
        if (exception != null) return Invalid(exception)

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

        // mixed numbers
        val first = data.substring(
            startIndex = 0,
            endIndex = indexOfOperatorStart-1,
        ).toMixedNumber()

        if (first.denominator == 0 && first.numerator != 0)
            return Invalid(FractionInvalidException("${first.numerator}/${first.denominator} not a valid fraction"))

        val second = data.substring(
            startIndex = indexOfOperatorEnd+1,
            endIndex = data.length,
        ).toMixedNumber()

        if (second.denominator == 0 && second.numerator != 0)
            return Invalid(FractionInvalidException("${second.numerator}/${second.denominator} not a valid fraction"))

        return Calculation(
            first = first,
            operation = operator,
            second = second,
        )
    }
}

