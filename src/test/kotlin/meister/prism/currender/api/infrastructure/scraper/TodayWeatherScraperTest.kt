package meister.prism.currender.api.infrastructure.scraper

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TodayWeatherScraperTest {
    @Test
    fun getTodayWeatherEntityTest() {
        println(Gson().toJson(TodayWeatherScraper().scrape()))
    }
}