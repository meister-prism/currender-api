package meister.prism.currender.api.application.resource

class TodayWeatherPayload(
        val code: Int,
        val text: String,
        val temperature: Temperature,
        val chanceOfRain: Double,
        val date: String
){
        class Temperature(
                max: Double,
                min: Double
        )
}
