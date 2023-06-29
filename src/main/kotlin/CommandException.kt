
sealed class CommandException(message: String): Exception(message) {
    class InvalidInputException(message: String): CommandException(message)
    class InvalidWhitespaceException(message: String): CommandException(message)
    class OperatorFormattingException(message: String): CommandException(message)
}