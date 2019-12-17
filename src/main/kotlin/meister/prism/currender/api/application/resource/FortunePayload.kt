package meister.prism.currender.api.application.resource

class FortunePayload(
        val FortunesArray: Array<Fortune>
){
    class Fortune(
            val sign: String // 何座
    )
}