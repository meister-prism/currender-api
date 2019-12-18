package meister.prism.currender.api.infrastructure.entity

class TrainEntity(
        val train: ArrayList<TrainEntityElement>
){
    class TrainEntityElement(
            val line: String,
            val serviceStatus: String,
            val description: String
    )
}
