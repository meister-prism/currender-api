package meister.prism.currender.api.infrastructure.scraper

import com.google.gson.Gson
import meister.prism.currender.api.infrastructure.entity.WeeklyWeatherForecastEntity
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class WeeklyWeatherForecastScraperTest {
    @Test
    fun getWeeklyWeatherForecastEntityTest(): Unit {
        val t = WeeklyWeatherForecastScraper()
        val a: WeeklyWeatherForecastEntity = t.scrape()
        println(Gson().toJson(a))
    }
}