package meister.prism.currender.api.domain.service

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
        mainTimer?.schedule(0,1000,({
            println(count)
            count++
        }))
    }

    fun stop(): Unit {
        mainTimer?.cancel()
        mainTimer = null
    }
}