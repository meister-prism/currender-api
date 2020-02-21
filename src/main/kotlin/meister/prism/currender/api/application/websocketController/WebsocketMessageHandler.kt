package meister.prism.currender.api.application.websocketController

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

/**
 * WebSocketでメッセージを受け取ったときの動作を示す。
 */
class WebsocketMessageHandler: TextWebSocketHandler() {
    companion object {
        var users: ArrayList<WebSocketSession> = ArrayList<WebSocketSession>()
    }
    private val periodicallyController: PeriodicallyController = PeriodicallyController()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        // 接続したら呼ばれる
        users.add(session)
        try {
//            users.forEach {it.sendMessage(TextMessage("hello!"))}
            if(users.size == 1){
                periodicallyController.run()
            }
        } catch (e: Exception) {
            session.sendMessage(TextMessage(e.toString()))
            session.close()
        }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // WebSocketクライアントからメッセージを受信した時に呼ばれる.
        try {
            users.forEach {it.sendMessage(message)}
        } catch (e: Exception) {
            session.sendMessage(TextMessage("sendMessage: Error"))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        // 接続が切られたら呼ばれる.
        if (users.size==1){
            periodicallyController.stop()
        }
        var rem = users
                .filter { user -> user.id == session.id }
                .first()
        users.remove(rem)
        var targets = users.filter { user -> user.id != session.id }
        targets.forEach {it.sendMessage(TextMessage("Good Bye."))}
    }

    fun postMessage(message: String){
        try {
            println(message)
            users.forEach {it.sendMessage(TextMessage(message))}
        } catch (e: Exception) {
            println("hogehoge")
        }
    }
}