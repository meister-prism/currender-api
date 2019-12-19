package meister.prism.currender.api.infrastructure.apiClient

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FortuneApiClientTest{
    @Test
    fun getFortuneEntityTest(){
        val t = FortuneApiClient()
        val a = t.getFortuneEntity()
        println(Gson().toJson(a).toString())
    }
    @Test
    fun test(){
        val t = FortuneApiClient()
    }
}