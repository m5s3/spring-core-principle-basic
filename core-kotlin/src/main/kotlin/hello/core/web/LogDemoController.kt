package hello.core.web

import hello.core.common.MyLogger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class LogDemoController(
    private val logDemoService: LogDemoService,
    private val myLogger: MyLogger,
) {

    @RequestMapping("log-demo")
    @ResponseBody
    fun logDemo(request: HttpServletRequest): String {
        val requestURL: String = request.requestURI.toString()
        myLogger.requestURL = requestURL
        myLogger.log("controller test")
        logDemoService.logic("testId")
        return "OK"
    }
}