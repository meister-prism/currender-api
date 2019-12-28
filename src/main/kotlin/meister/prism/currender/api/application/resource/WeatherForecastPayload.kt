package meister.prism.currender.api.application.resource

class WeatherForecastPayload (
    val weathers: ArrayList<Weather>
){
    class Weather(
            val text: String,
            val temperature: Temperature,
            val chanceOfRain: String,
            val date: String
    ){
        class Temperature(
                max: Double,
                min: Double
        )
    }
}