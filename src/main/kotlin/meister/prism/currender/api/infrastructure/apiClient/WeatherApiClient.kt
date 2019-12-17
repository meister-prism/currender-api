package meister.prism.currender.api.infrastructure.apiClient

import meister.prism.currender.api.infrastructure.entity.TodayWeatherEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

class WeatherApiClient {
    private val restTemplate = RestTemplate()
    private val cityId = 130010
    private val endPoint = "http://weather.livedoor.com/forecast/webservice/json/v1?city=$cityId"
    fun getTodayWeatherEntity(): TodayWeatherEntity? {
        val res: TodayWeatherEntity? = restTemplate.getForObject(this.endPoint, TodayWeatherEntity::class.java)
        if(res != null) {
            return res
        }
        // null後の処理
        return null
    }
}