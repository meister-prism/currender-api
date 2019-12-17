package meister.prism.currender.api.application.resource

class TrafficPayload (
        val TrafficsArray: Array<TrafficData>
){
    class TrafficData(
        val line: String,
        val type: String,
        val text: String,
        val delayTime: Int?
    )
}