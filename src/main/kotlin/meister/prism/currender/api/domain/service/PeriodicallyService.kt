package meister.prism.currender.api.domain.service

import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import java.util.*
import kotlin.concurrent.schedule

class PeriodicallyService {
    /**
     * 定期実行処理が含まれています
     */
    private var mainTimer: Timer? = Timer()
    private var count: Int = 0
    fun run(): Unit {
        if (mainTimer == null){
            mainTimer = Timer()
        }
        mainTimer?.schedule(0,30000,({
            periodicallyHandler()
        }))
    }

    fun stop(): Unit {
        mainTimer?.cancel()
        mainTimer = null
    }
    private fun periodicallyHandler() {
        val almanacService = AlmanacService()
        val fortuneService = FortuneService()
        val trafficService = TrafficService()
        val weatherService = WeatherService()
        val whatIsTodayService = WhatIsTodayService()
        almanacService.sendPayload()
        fortuneService.sendPayload()
        trafficService.sendPayload()
        weatherService.sendPayload()
        whatIsTodayService.sendPayload()
    }
}