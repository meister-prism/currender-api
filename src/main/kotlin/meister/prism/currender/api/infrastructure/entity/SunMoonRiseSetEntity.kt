package meister.prism.currender.api.infrastructure.entity

import java.time.Month
import javax.management.monitor.StringMonitor

class SunMoonRiseSetEntity (
        val result: Element
){
    class Element(
            val version: String,
            val date: Date,
            val location: Location,
            val rise_and_set: RiseAndSet
    ){
        class Date(
            val year: String,
            val month: String,
            val day: String
        )
        class Location(
            val coordinate: Coordinate
        ){
            class Coordinate(
                    val lat: String,
                    val lng: String
            )
        }
        class RiseAndSet(
            val sunrise: String,
            val sunset: String,
            val sunrise_hm: String,
            val sunset_hm: String
        )
    }

}