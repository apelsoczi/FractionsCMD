package model

import CommandException

/** represent a command to send to the calculator */
sealed class Command {

    /** usually C / CE to clear input */
    object Clear : Command()

    /** performs a math function */
    data class Calculation(
        val firstFraction: String,
        val operation: String,
        val secondFraction: String,
    ) : Command()

    /** displays errors to the user */
    data class Invalid(
        val exception: CommandException,
    ) : Command()

    /** power off the calculator */
    object ExitProcess : Command()

}
