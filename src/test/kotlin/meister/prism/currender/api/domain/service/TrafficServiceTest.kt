package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrafficServiceTest {
    @Test
    fun getTrafficPayloadTest(){
        println(Gson().toJson(TrafficService().getTrafficPayload()))
    }
}