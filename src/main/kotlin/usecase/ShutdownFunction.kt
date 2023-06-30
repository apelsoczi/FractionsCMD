package usecase

import kotlin.system.exitProcess

class ShutdownFunction(
    exitHandler: SystemExit? = null
) {

    // exit handler for /test coverage when calling System.exit()
    private val systemExit = exitHandler ?: object : SystemExit {
        override fun exit(): Nothing {
            exitProcess(0)
        }
    }

    fun invoke() {
        println(PROMPT_SHUTDOWN)
        systemExit.exit()
    }

    companion object {
        val PROMPT_SHUTDOWN = "... shutting down"
    }

}

interface SystemExit {
    fun exit()
}