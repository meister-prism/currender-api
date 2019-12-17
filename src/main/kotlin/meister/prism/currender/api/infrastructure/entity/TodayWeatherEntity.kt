package meister.prism.currender.api.infrastructure.entity

class TodayWeatherEntity (
        val endPoint: String = "http://weather.livedoor.com/forecast/webservice/json/v1",
        val forecasts: Array<Forecast>,
        val publicTime: String,
        val title: String,
        val description: Description,
        val link: String,
        val location: Location
){
    class Description(
            val text: String,
            val publicTime: String
    )
    class Forecast(
            /**
             * YYYY-MM-DD
             */
            val date: String,
            val dateLabel: String,
            val telop: String,
            val temperature: Temperature
    ){
        class Temperature(
                val min: Temp,
                val max: Temp
        )
        class Temp(
                val celsius: String,
                val fahrenheit: String
        )
    }
    class Location(
            val city: String,
            val area: String,
            val prefecture: String
    )
}