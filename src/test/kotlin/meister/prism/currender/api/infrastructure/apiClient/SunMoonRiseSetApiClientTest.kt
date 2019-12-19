package meister.prism.currender.api.infrastructure.apiClient

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SunMoonRiseSetApiClientTest{
    @Test
    fun test(){
        val t = SunMoonRiseSetApiClient()
        val r = t.getSunMoonRiseSetEntity()
        println(Gson().toJson(r))
    }
}