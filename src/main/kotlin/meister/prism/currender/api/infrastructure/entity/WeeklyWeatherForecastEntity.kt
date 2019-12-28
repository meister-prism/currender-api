package meister.prism.currender.api.infrastructure.entity

class WeeklyWeatherForecastEntity (
        val city: String,
        val dates: ArrayList<String>,
        val days: ArrayList<String>,
        val chanceOfRains: ArrayList<String>,
        val maxTemperatures: ArrayList<String>,
        val minTemperatures: ArrayList<String>,
        val weathers: ArrayList<String>
)
