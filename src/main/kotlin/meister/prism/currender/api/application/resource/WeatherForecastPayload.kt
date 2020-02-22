package meister.prism.currender.api.application.resource

class WeatherForecastPayload (
    val weathers: ArrayList<Weather>
){
    class Weather(
            val text: String,
            val maxTemparature: String,
            val minTemparature: String,
            val chanceOfRain: String,
            val date: String
    )
}