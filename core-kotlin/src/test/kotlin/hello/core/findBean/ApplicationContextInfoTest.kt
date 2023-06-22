package hello.core.findBean

import hello.core.AppConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest {
    private val ac = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("모든 빈 출력하기")
    fun findAllBean() {
        for (beanName in ac.beanDefinitionNames) {
            val bean = ac.getBean(beanName)
            println("name = $beanName bean = $bean")
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    fun findApplicationBean() {
        for (beanName in ac.beanDefinitionNames) {
            val beanDefinition = ac.getBeanDefinition(beanName)
            if (beanDefinition.role == BeanDefinition.ROLE_APPLICATION) {
                val bean = ac.getBean(beanName)
                println("beanName = $beanName bean = $bean")
            }
        }
    }
}