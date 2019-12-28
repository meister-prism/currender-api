package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.resource.TrafficPayload
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.env.TrafficEnvironment
import meister.prism.currender.api.infrastructure.entity.TrainEntity
import meister.prism.currender.api.infrastructure.scraper.TrainScraper
import org.springframework.stereotype.Service

class TrafficService{
    private val trainScraper= TrainScraper()
    private val registerTrains = TrafficEnvironment.trains
    fun getTrafficPayload(): TrafficPayload {
        val trainResponse = trainScraper.scrape()
        val ret: ArrayList<TrafficPayload.TrainData> = ArrayList()
        // 登録路線の中で遅延が発生しているものをretに追加する
        for(e: TrainEntity.TrainEntityElement in trainResponse.train){
            if(e.line in registerTrains){
                ret.add(TrafficPayload.TrainData(e.line,e.serviceStatus,e.description))
            }
        }
        lateinit var message: String
        if(ret.size == 0){
            message = "登録路線で運行情報はありません"
        }else{
            message = "登録路線で運行情報があります。"
        }
        return TrafficPayload(
                ret,trainMessage=message
        )
    }

    private val messageHandler = WebsocketMessageHandler()
    fun sendPayload(){
        val body = EventBody(
                EventName.TRAFFIC,getTrafficPayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
    }
}