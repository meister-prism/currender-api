package meister.prism.currender.api.application.resource

class WeatherForecastPayload (
    val weathersArray: Array<Weather>
){
    class Weather(
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
}