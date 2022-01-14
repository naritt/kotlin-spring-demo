package demo.controller

import demo.model.PingResponse
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Api(tags = ["ping"])
class PingContoller {
    @GetMapping("/ping")
    fun ping(): PingResponse {
        return PingResponse()
    }
}