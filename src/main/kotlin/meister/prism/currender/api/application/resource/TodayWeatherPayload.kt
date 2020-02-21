package meister.prism.currender.api.application.resource

class TodayWeatherPayload(
        val title: String,
        val description: String,
        val maxTemperature: String,
        val minTemperature: String,
        val chanceOfRains: ArrayList<String>,
        val date: String
)
