package meister.prism.currender.api.application.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @RequestMapping("/")
    fun hello(): String{
        return "hello"
    }
}