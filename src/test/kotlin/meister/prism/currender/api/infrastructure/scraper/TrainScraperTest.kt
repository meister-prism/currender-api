package meister.prism.currender.api.infrastructure.scraper

import com.google.gson.Gson
import meister.prism.currender.api.infrastructure.entity.TrainEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class TrainScraperTest {
    @Test
    fun getTrainEntityTest(){
        val t = TrainScraper()
        print(Gson().toJson(t.scrape()))
    }

    @Test
    fun getDescriptionTest(){
        val t = TrainScraper()
        var method =  t::class.java.getDeclaredMethod("getTroubleDescription")
        method.trySetAccessible()
        assertEquals(null,method.invoke(t))
    }
}