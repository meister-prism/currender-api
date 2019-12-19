package meister.prism.currender.api.infrastructure.apiClient

import com.google.gson.Gson
import org.junit.jupiter.api.Test

internal class MoonAgeApiClientTest {
    @Test
    fun getMoonAgeEntityTest(){
        val t = MoonAgeApiClient()
        val r = t.getMoonAgeEntity()
        println(Gson().toJson(r))
    }
}