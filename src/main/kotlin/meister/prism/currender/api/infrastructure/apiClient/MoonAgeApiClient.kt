package meister.prism.currender.api.infrastructure.apiClient

import com.google.gson.Gson
import meister.prism.currender.api.infrastructure.entity.MoonAgeEntity
import org.json.XML
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MoonAgeApiClient {
    private val restTemplate = RestTemplate()
    private val endPoint: String = "http://labs.bitmeister.jp/ohakon/api/?mode=moon_age"

    fun getMoonAgeEntity(): MoonAgeEntity {
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val year: String = now.split("-")[0]
        val month: String = now.split("-")[1]
        val date: String = now.split("-")[2]

        val res: String? = restTemplate.getForObject(
                "$endPoint&year=$year&month=$month&day=$date",
                String::class.java)
        if (res != null) {
            val s: String = XML.toJSONObject(res).toString()
            return Gson().fromJson(s,MoonAgeEntity::class.java)
        }
        throw Error()
    }
}