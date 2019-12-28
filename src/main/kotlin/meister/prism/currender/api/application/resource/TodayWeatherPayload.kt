package meister.prism.currender.api.application.resource

class TodayWeatherPayload(
        val title: String,
        val description: String,
        val temperature: Temperature,
        val chanceOfRains: ArrayList<String>,
        val date: String
){
        class Temperature(
                max: Double,
                min: Double
        )
}
