package meister.prism.currender.api.application.resource

class AlmanacPayload(
        val date: String, // yyyy-MM-dd
        val moonAge: Double, // 月齢
        val riseSet: SunMoonRiseSet
){
    class SunMoonRiseSet(
            val sunriseTime: String, // HH-mm
            val sunsetTime: String, // HH-mm
            val moonriseTime: String, // HH-mm
            val moonsetTime: String // HH-mm
    )
}