package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.AlmanacPayload
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.infrastructure.apiClient.MoonAgeApiClient
import meister.prism.currender.api.infrastructure.apiClient.SunMoonRiseSetApiClient
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AlmanacService {
    val moonAgeApiClient = MoonAgeApiClient()
    val sunMoonRiseSetApiClient = SunMoonRiseSetApiClient()
    fun getAlmanacPayload(): AlmanacPayload {
        val moonAgeResponse = moonAgeApiClient.getMoonAgeEntity()
        val sunMoonRiseSetResponse = sunMoonRiseSetApiClient.getSunMoonRiseSetEntity()
        val riseAndSet = sunMoonRiseSetResponse.result.rise_and_set
        val sunMoonRiseSet = AlmanacPayload.SunMoonRiseSet(
                riseAndSet.sunrise_hm,
                riseAndSet.sunset_hm,
                riseAndSet.moonrise_hm,
                riseAndSet.moonset_hm
        )
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        return AlmanacPayload(
                now,moonAgeResponse.result.moon_age.toDouble(),sunMoonRiseSet
        )
    }
    private val messageHandler = WebsocketMessageHandler()
    fun sendPayload(){
        val body = EventBody(
                EventName.ALMANAC,getAlmanacPayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
    }
}