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
                val sunrise: String, // H.m/60のDouble
                val sunset: String, // H.m/60のDouble
                val sunrise_hm: String, // Hm: HH:mm のデータ
                val sunset_hm: String, // Hm: HH:mm のデータ
                val moonrise: String,  // H.m/60のDouble
                val moonset: String, // H.m/60のDouble
                val moonrise_hm: String, // Hm: HH:mm のデータ
                val moonset_hm: String // Hm: HH:mm のデータ
        )
    }

}