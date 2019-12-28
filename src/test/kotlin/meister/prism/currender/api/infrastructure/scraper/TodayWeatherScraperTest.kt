package meister.prism.currender.api.infrastructure.scraper

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TodayWeatherScraperTest {
    @Test
    fun scrapeTest(){
        val t = TodayWeatherScraper()
        val a = t.scrape()
        println(Gson().toJson(a))
    }
}