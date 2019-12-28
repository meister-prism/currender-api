package meister.prism.currender.api.infrastructure.apiClient

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import meister.prism.currender.api.infrastructure.entity.FortuneEntity
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class FortuneApiClient {
    private val restTemplate = RestTemplate()
    private val endPoint: String = "http://api.jugemkey.jp/api/horoscope/free/" //+ yyyy/mm/dd

    fun getFortuneEntity(): FortuneEntity {
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
        val res: Any? = restTemplate.getForObject(this.endPoint+now, Any::class.java)
        if(res != null) {
            val responseObject: JsonObject = Gson().fromJson((Gson().toJson(res)),JsonObject::class.java)
            val tmp: FortuneEntity? = entityMapping(responseObject)
            if(tmp != null) return tmp
        }
        throw Error()
    }

    private fun entityMapping(responseObject: JsonObject): FortuneEntity?{
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
        val moment: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val body: JsonArray = responseObject.getAsJsonObject("horoscope").getAsJsonArray(now) ?: return null
        var horoscopes: ArrayList<FortuneEntity.Horoscope> = ArrayList()
        for(e: JsonElement in body){
//            println(e.asJsonObject["sign"].toString().replace("\"",""))
            horoscopes.add(
                    FortuneEntity.Horoscope(
                            e.asJsonObject["sign"].toString().replace("\"",""),
                            e.asJsonObject["rank"].toString().replace("\"",""),
                            e.asJsonObject["money"].toString().replace("\"",""),
                            e.asJsonObject["total"].toString().replace("\"",""),
                            e.asJsonObject["job"].toString().replace("\"",""),
                            e.asJsonObject["love"].toString().replace("\"",""),
                            e.asJsonObject["color"].toString().replace("\"",""),
                            e.asJsonObject["content"].toString().replace("\"","")
                    )
            )
        }
        return FortuneEntity(horoscopes, moment)
    }
}