package meister.prism.currender.api.infrastructure.entity

class TodayWeatherEntity (
        val chanceOfRains: ArrayList<String>,
        val temperature: Temperature,
        val title: String,
        val info: String
){
    val endPoint: String = "https://www.jma.go.jp/jp/yoho/319.html"
    class Temperature(
            val morningMin: String,
            val daytimeMax: String
    )
}