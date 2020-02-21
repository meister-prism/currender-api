package meister.prism.currender.api.infrastructure.scraper

import meister.prism.currender.api.infrastructure.entity.TodayWeatherEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * 気象庁のHPから東京都の今日の天気をスクレイピングします
 */
class TodayWeatherScraper {
    private val endPoint = "https://www.jma.go.jp/jp/yoho/319.html"
    fun scrape(): TodayWeatherEntity {
        val document: Document = Jsoup.connect(endPoint).get()
        val chanceOfRains: Elements = document.select("table.rain")
        val temperatures: Elements = document.select("td.temp")
        val weatherImageElements: Elements = document.select("th.weather")
        val info: Elements = document.select("td.info")
        val imgAltText: String = weatherImageElements.eq(0).select("img").attr("alt")
        return entityMapping(imgAltText, chanceOfRains.eq(0), temperatures.eq(0), info.eq(0).text())
    }

    private fun entityMapping(imgAltText: String, chanceOfRains: Elements, temperature: Elements, info: String): TodayWeatherEntity {
        val chanceOfRainsList: ArrayList<String> = ArrayList()
        for(e: Element in chanceOfRains.select("tr")){
            chanceOfRainsList.add(e.select("td").text().split(" ")[1])
        }
        val tmpMin: String = temperature.select("tr").eq(1).select("td.min").text()
        val tmpMax: String = temperature.select("tr").eq(1).select("td.max").text()
        val t = TodayWeatherEntity.Temperature(tmpMin,tmpMax)
        return TodayWeatherEntity(chanceOfRainsList,t,imgAltText,info)
    }
}