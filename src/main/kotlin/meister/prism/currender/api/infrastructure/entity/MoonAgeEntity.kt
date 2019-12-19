package meister.prism.currender.api.infrastructure.entity

class MoonAgeEntity(
        val result: Element
) {
    class Element(
            val version: String,
            val date: Date,
            val moon_age: String
    ){
        class Date (
                val year: String,
                val month: String,
                val day: String
        )
    }
}