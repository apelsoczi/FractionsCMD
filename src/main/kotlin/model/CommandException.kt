package model
/**
 * represents exceptions which occur by user input
 *
 * @param message the message logged to the user.
 */
sealed class CommandException(message: String): Exception(message) {
    class InvalidInputException(message: String): CommandException(message)
    class InvalidWhitespaceException(message: String): CommandException(message)
    class OperatorFormattingException(message: String): CommandException(message)
    class FractionInvalidException(message: String): CommandException(message)
}