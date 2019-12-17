package meister.prism.currender.api.application.resource

class WhatIsTodayPayload(
    val TodayDataArray: Array<Today>
){
    class Today(
            val date: String,
            val title: String,
            val description: String
    )
}