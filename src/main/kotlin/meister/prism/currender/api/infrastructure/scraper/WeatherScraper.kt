package meister.prism.currender.api.infrastructure.scraper

import meister.prism.currender.api.infrastructure.entity.WeeklyWeatherForecastEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * 気象庁のHPから東京都の天気をスクレイピングします。
 */
class WeatherScraper {
    private val endPoint: String = "https://www.jma.go.jp/jp/week/319.html"
    // 週間天気を取得するなど
    fun scrape(): WeeklyWeatherForecastEntity {
        val document: Document = Jsoup.connect(endPoint).get()
        // 都市一覧
        val city: Elements = document.select("th.cityname")
        // 日付と曜日
        val days: Elements = document.select("table#infotablefont").select("tr").first().select("th")
        // 降水確率
        val chanceOfRains: Elements = document.select("table#infotablefont").select("tr").eq(2).select("td")
        // 最高・最低気温
        val maxTemps: Elements = document.select("table#infotablefont").select("tr").eq(4).select("td")
        val minTemps: Elements = document.select("table#infotablefont").select("tr").eq(5).select("td")
        val cityname: String = city.eq(0).text()
        return entityMapping(cityname, days, chanceOfRains, maxTemps, minTemps)
    }

    private fun entityMapping(cityname: String, days: Elements,chanceOfRains: Elements,
                      maxTemps: Elements, minTemps: Elements): WeeklyWeatherForecastEntity {
        // days: <th class="weekday">19<br>木</th> -> 19
        var dates: ArrayList<String> = ArrayList()
        var dys: ArrayList<String> = ArrayList()
        var cor: ArrayList<String> = ArrayList()
        var mxt: ArrayList<String> = ArrayList()
        var mnt: ArrayList<String> = ArrayList()
        var cnt:Int = 0
        for(e in days){
            if(e.text() != "日付"){
                cnt++
                dates.add(e.text().split(' ')[0])
                dys.add(e.text().split(' ')[1])
            }
        }
        for(i:Int in 1..cnt){
            cor.add(chanceOfRains.eq(i).text())
            mxt.add(maxTemps.eq(i).text().replace("\\(.*\\)".toRegex(),""))
            mnt.add(minTemps.eq(i).text().replace("\\(.*\\)".toRegex(),""))
        }
        return WeeklyWeatherForecastEntity(cityname,dates,dys,cor,mxt,mnt)
    }
}