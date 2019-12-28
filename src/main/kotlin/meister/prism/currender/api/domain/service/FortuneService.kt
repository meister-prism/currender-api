package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.resource.FortunePayload
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.infrastructure.apiClient.FortuneApiClient
import meister.prism.currender.api.infrastructure.entity.FortuneEntity
import org.springframework.stereotype.Service
class FortuneService {
    val fortuneApiClient = FortuneApiClient()

    fun getFortunePayload(): FortunePayload{
        val res: FortuneEntity = fortuneApiClient.getFortuneEntity()
        var fortunesArray: ArrayList<FortunePayload.Fortune> = ArrayList()
        for(e in res.horoscopes){
            val score = FortunePayload.Fortune.Score(
                    e.rank.toInt(),e.total.toInt(),e.money.toInt(),e.job.toInt(),e.love.toInt()
            )
            fortunesArray.add(FortunePayload.Fortune(e.sign,e.content,e.color,score))
        }
        return FortunePayload(fortunesArray,res.date)
    }

    private val messageHandler = WebsocketMessageHandler()
    fun sendPayload(){
        val body = EventBody(
                EventName.FORTUNE,getFortunePayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
    }
}