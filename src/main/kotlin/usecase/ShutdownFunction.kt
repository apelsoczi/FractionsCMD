package usecase

import kotlin.system.exitProcess

class ShutdownFunction {

    fun invoke() {
        println("... shutting down")
        exitProcess(0)
    }

}