package usecase

import CommandException

class ErrorFunction {

    fun invoke(exception: CommandException) {
        println("Error: ${exception.message}")
    }

}