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
        )
        if (!first.matches(Regex(REGEX_MIXED_NUMBER)))
            return Invalid(FractionInvalidException("Format input as whole number 'X' and '&' fraction 'Y/Z'"))
        val firstMixedNumber = first.toMixedNumber()
        if (firstMixedNumber.denominator == 0)
            return Invalid(FractionInvalidException("${firstMixedNumber.numerator}/${firstMixedNumber.denominator} not a valid fraction"))

        val second = data.substring(
            startIndex = indexOfOperatorEnd+1,
            endIndex = data.length,
        )
        if (!second.matches(Regex(REGEX_MIXED_NUMBER)))
            return Invalid(FractionInvalidException("Format input as whole number 'X' and '&' fraction 'Y/Z'"))
        val secondMixedNumber = second.toMixedNumber()
        if (secondMixedNumber.denominator == 0)
            return Invalid(FractionInvalidException("${secondMixedNumber.numerator}/${secondMixedNumber.denominator} not a valid fraction"))

        return Calculation(
            first = firstMixedNumber,
            operation = operator,
            second = secondMixedNumber,
        )
    }

    companion object {
        val REGEX_MIXED_NUMBER = "^(-?\\d+)&(-?\\d+)/(-?\\d+)\$|^(-?\\d+)\$|^(-?\\d+)/(-?\\d+)\$"
    }
}

