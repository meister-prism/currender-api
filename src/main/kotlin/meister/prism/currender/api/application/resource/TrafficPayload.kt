package meister.prism.currender.api.application.resource

class TrafficPayload (
        val train: ArrayList<TrainData>,
        val trainMessage: String
){
    class TrainData(
        val line: String,
        val serviceStatus: String,
        val description: String
    )
}