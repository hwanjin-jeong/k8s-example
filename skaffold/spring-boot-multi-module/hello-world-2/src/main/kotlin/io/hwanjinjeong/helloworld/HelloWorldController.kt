package io.hwanjinjeong.helloworld

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class HelloWorldController {

    @GetMapping
    fun hello() = "Hello! World!"
}