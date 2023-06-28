package hello.core.lifecycle

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

class NetworkClient(
    var url: String = "",
) {

    @PostConstruct
    fun create() {
        println("NetworkClient.afterPropertiesSet")
        connect()
        call("초기화 연결 메세지")
    }

    @PreDestroy
    fun close() {
        println("NetworkClient.destroy")
        disconnect()
    }

    init {
        println("초기화 호출, url = ${url}")
    }
    fun connect() {
        println("connect = ${url}")
    }

    fun call(message: String) {
        println("call = ${url} message = $message")
    }

    fun disconnect() {
        println("close: $url")
    }
}