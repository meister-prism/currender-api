package meister.prism.currender.api.infrastructure.scraper

import meister.prism.currender.api.infrastructure.entity.TrainEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class TrainScraper {
    // https://.../area/id/にリクエスト,　id: 2-9
    private val endPoint: String = "https://transit.yahoo.co.jp/traininfo/area/"
    fun scrape(): TrainEntity{
        var troubleDescriptions: ArrayList<TrainEntity.TrainEntityElement> = ArrayList()
        for(i:Int in 2..9){
            val document: Document = Jsoup.connect("$endPoint$i/").get()
            val troubles: Elements = document.select("div.elmTblLstLine.trouble").select("table").select("a")
            for(e: Element in troubles){
                var url = e.attr("href")
                troubleDescriptions.add(getTroubleDescription(url))
            }
        }
        return entityMapping(troubleDescriptions)
    }

    private fun getTroubleDescription(url: String): TrainEntity.TrainEntityElement {
        val document: Document = Jsoup.connect(url).get()
        val line: String = document.select("h1").text()
        var serviceStatus: String = document.select("div#mdServiceStatus").select("dt").text()
        serviceStatus = serviceStatus.replace("\\[.*\\]".toRegex(),"")
        var description: String = document.select("div#mdServiceStatus").select("dd").text()
        description = description.replace("（.*）".toRegex(),"")
        return TrainEntity.TrainEntityElement(line,serviceStatus,description)
    }

    private fun entityMapping(troubleDescriptions: ArrayList<TrainEntity.TrainEntityElement>): TrainEntity {
        return TrainEntity(troubleDescriptions)
    }
}