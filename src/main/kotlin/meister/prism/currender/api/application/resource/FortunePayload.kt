package meister.prism.currender.api.application.resource

class FortunePayload(
        val fortunesArray: ArrayList<Fortune>,
        val date: String // yyyy-MM-dd
){
    class Fortune(
            val sign: String, // 何座
    	    val content: String, // 占い内容
            val color: String, // ラッキーカラー
            val score: Score
    ){
        class Score (
                val rank: Int,
                val total: Int,
                val money: Int,
                val job: Int,
                val love: Int
        )
    }
}