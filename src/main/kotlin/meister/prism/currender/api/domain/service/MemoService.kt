package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.resource.MemoImgBody
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.infrastructure.entity.ImageMemoEntity
import meister.prism.currender.api.infrastructure.repository.MemoRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MemoService {
    fun postMemo(data: MemoImgBody): String {
        val now: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")).toString()

        return MemoRepository().addImg(ImageMemoEntity(now,data.handwrittenImg.replace(" " ,"SPACE")))
    }
    private val messageHandler = WebsocketMessageHandler()
    fun sendAllMemos(entity: ArrayList<ImageMemoEntity>) {
        for(i in entity){
            i.imgMemo = i.imgMemo.replace("SPACE"," ")
        }
        val body = EventBody(
                EventName.HANDWRITTEN_MEMO_CHANGED,entity
        )
        messageHandler.postMessage(Gson().toJson(body))
    }
}