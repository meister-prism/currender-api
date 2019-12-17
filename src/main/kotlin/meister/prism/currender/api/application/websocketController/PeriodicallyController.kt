package meister.prism.currender.api.application.websocketController

import meister.prism.currender.api.domain.service.PeriodicallyService

class PeriodicallyController{
    private val periodicallyService = PeriodicallyService()
    fun run(): Unit {
        periodicallyService.run()
    }
    fun stop(): Unit {
        periodicallyService.stop()
    }
}