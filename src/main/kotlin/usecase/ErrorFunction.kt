package usecase

import model.CommandException

class ErrorFunction {

    fun invoke(exception: CommandException) {
        println("$PROMPT_ERROR ${exception.message}")
    }

    companion object {
        val PROMPT_ERROR = "Error:"
    }

}