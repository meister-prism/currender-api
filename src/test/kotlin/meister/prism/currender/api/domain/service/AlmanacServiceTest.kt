package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.AlmanacPayload
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AlmanacServiceTest {
    @Test
    fun getAlmanacPayloadTest(){
        val t = AlmanacService().getAlmanacPayload()
        println(Gson().toJson(t))
    }
}