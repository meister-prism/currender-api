package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FortuneServiceTest  {
    @Test
    fun getFortunePayloadTest(){
        println(Gson().toJson(FortuneService().getFortunePayload()))
    }
}