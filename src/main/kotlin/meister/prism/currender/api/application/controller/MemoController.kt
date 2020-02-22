package meister.prism.currender.api.application.controller

import meister.prism.currender.api.application.resource.MemoImgBody
import meister.prism.currender.api.domain.service.MemoService
import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class MemoController {

    @CrossOrigin
    @GetMapping("/memo")
    fun test(): String {
        return "fin"
    }

    @CrossOrigin
    @RequestMapping(value= ["/handwrittenImg/add"], method = [RequestMethod.POST], headers = ["Accept=application/x-www-form-urlencoded"])
    fun setMemoImg(@RequestBody memoImg: MemoImgBody): String {
        println(memoImg.handwrittenImg)
        return MemoService().postMemo(memoImg)
    }

    @RequestMapping(value= ["/memo"], method = [RequestMethod.POST], headers = ["Accept=application/x-www-form-urlencoded"])
    fun addHandwrittenImg(@RequestBody memoImg: MemoImgBody): String {
        println(memoImg.handwrittenImg)

        return "OK"
    }

}