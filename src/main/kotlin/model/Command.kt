package model

/** represent a command to send to the calculator */
sealed class Command {

    /** usually C / CE to clear input */
    object Clear : Command()

    /** performs a math function */
    data class Calculation(
        val first: MixedNumber,
        val operation: String,
        val second: MixedNumber,
    ) : Command()

    /** displays errors to the user */
    data class Invalid(
        val exception: CommandException,
    ) : Command()

    /** power off the calculator */
    object ExitProcess : Command()

}
