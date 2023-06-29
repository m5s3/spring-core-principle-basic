package hello.core.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.*

@Component
@Scope(value="request")
class MyLogger(
    var uuid: String = "",
    var requestURL: String = "",
) {
    fun log(message: String) {
        println("[$uuid][$requestURL] $message")
    }

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        println("[$uuid] request scope bean create : $this")
    }

    @PreDestroy
    fun close() {
        println("[$uuid] request scope bean close : $this")
    }
}