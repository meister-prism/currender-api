package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import meister.prism.currender.api.application.resource.EventBody
import meister.prism.currender.api.application.resource.TodayWeatherPayload
import meister.prism.currender.api.application.resource.WeatherForecastPayload
import meister.prism.currender.api.application.websocketController.WebsocketMessageHandler
import meister.prism.currender.api.constant.EventName
import meister.prism.currender.api.infrastructure.scraper.TodayWeatherScraper
import meister.prism.currender.api.infrastructure.scraper.WeeklyWeatherForecastScraper
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
class WeatherService {
    private val todayWeatherScraper = TodayWeatherScraper()
    private val weeklyWeatherForecastScraper = WeeklyWeatherForecastScraper()
    fun getTodayWeatherPayload(): TodayWeatherPayload {
        val res = todayWeatherScraper.scrape()
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        val tmp =  TodayWeatherPayload.Temperature(
                res.temperature.daytimeMax,
                res.temperature.morningMin
        )
        println(res.temperature.daytimeMax)
        return TodayWeatherPayload(
                res.title,res.info,
                tmp,
                res.chanceOfRains,
                now
        )
    }

    fun getWeatherForecastPayload(): WeatherForecastPayload{
        val res = weeklyWeatherForecastScraper.scrape()
        val now: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        var weathers: ArrayList<WeatherForecastPayload.Weather> = ArrayList()
        for(i:Int in res.dates.indices){
//            val temp = WeatherForecastPayload.Weather.Temperature(res.maxTemperatures[i],res.minTemperatures[i])
            val temp = WeatherForecastPayload.Weather.Temperature("ww","mm")
            weathers.add(WeatherForecastPayload.Weather(
                    res.weathers[i],
                    temp,
                    res.chanceOfRains[i],
                    now
            ))
        }
        return WeatherForecastPayload(
                weathers
        )
    }

    private val messageHandler = WebsocketMessageHandler()
    fun sendPayload(){
        var body = EventBody(
                EventName.WEATHER_TODAY,getTodayWeatherPayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
        body = EventBody(
                EventName.WEATHER_FORECAST,getWeatherForecastPayload()
        )
        messageHandler.postMessage(Gson().toJson(body))
    }


}