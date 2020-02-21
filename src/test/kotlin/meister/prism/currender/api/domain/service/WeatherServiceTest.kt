package meister.prism.currender.api.domain.service

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WeatherServiceTest {
    @Test
    fun getWeatherForecastPayloadTest(){
        println(Gson().toJson(WeatherService().getWeatherForecastPayload()))
    }

    @Test
    fun getTodayWeatherPayloadTest(){
        println(Gson().toJson(WeatherService().getTodayWeatherPayload()))
    }
}