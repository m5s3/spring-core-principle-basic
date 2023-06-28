package hello.core.lifecycle

import org.junit.jupiter.api.Test
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest {

    @Test
    fun lifeCycleTest() {
        val ac:ConfigurableApplicationContext = AnnotationConfigApplicationContext(LifeCycleConfig::class.java)
        val client: NetworkClient = ac.getBean(NetworkClient::class.java)
        ac.close()
    }


    @Configuration
    class LifeCycleConfig {
        @Bean(initMethod = "create", destroyMethod = "close")
        fun networkClient(): NetworkClient {
            val networkClient = NetworkClient();
            networkClient.url = "http://hello-spring.dev"
            return networkClient
        }
    }
}