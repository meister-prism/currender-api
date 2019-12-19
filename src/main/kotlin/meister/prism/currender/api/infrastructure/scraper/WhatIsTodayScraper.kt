package meister.prism.currender.api.infrastructure.scraper

import meister.prism.currender.api.infrastructure.entity.TrainEntity
import meister.prism.currender.api.infrastructure.entity.WhatIsTodayEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class WhatIsTodayScraper(){
    private val endPoint: String = "https://kids.yahoo.co.jp/today/"
    fun scrape(): WhatIsTodayEntity{
        val document: Document = Jsoup.connect(endPoint).get()
        val title: String = document.select("#dateDtl").select("dt").select("span").text()
        val description: String = document.select("#dateDtl").select("dt").text()
        return entityMapping(title,description)
    }

    private fun entityMapping(title: String, description: String): WhatIsTodayEntity {
        return WhatIsTodayEntity(title,description)
    }
}
