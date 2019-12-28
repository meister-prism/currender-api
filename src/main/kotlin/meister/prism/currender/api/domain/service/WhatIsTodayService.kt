package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.resource.WhatIsTodayPayload
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.infrastructure.scraper.WhatIsTodayScraper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WhatIsTodayService {
    private val whatIsTodayScraper = WhatIsTodayScraper()
    fun getWhatIsTodayPayload(): WhatIsTodayPayload {
        val res = whatIsTodayScraper.scrape()
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        return WhatIsTodayPayload(now,res.title,res.description)
    }

    private val messageHandler = WebsocketMessageHandler()
    fun sendPayload(){
        val body = EventBody(
                EventName.WHAT_IS_TODAY,getWhatIsTodayPayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
    }
}