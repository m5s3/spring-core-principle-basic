package hello.core.scope

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

class SingletonWithPrototype {
    @Test
    fun singletonClientUsePrototype() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java, ClientBean::class.java)
        val clientBean1 = ac.getBean(ClientBean::class.java)
        val count1: Int = clientBean1.logic()
        println("count1 = ${count1}")
        val clientBean2 = ac.getBean(ClientBean::class.java)
        val count2: Int = clientBean2.logic()
        println("count2 = ${count2}")

        assertThat(count1).isEqualTo(1)
        assertThat(count2).isEqualTo(1)
    }

    @Scope(SCOPE_SINGLETON)
    open class ClientBean(
        @Autowired
        private val prototypeProvider: ObjectProvider<PrototypeBean>
    ) {
        fun logic(): Int {
            val prototypeBean: PrototypeBean = prototypeProvider.getObject()
            println("prototypeBean = ${prototypeBean}")
            prototypeBean.addCount()
            return prototypeBean.count
        }
    }

    @Scope(SCOPE_PROTOTYPE)
    class PrototypeBean() {
        var count: Int = 0;
        fun addCount() {
            count++
        }
        @PostConstruct
        fun init() {
            println("PrototypeBean.init $this")
        }

        @PreDestroy
        fun destroy() {
            println("PrototypeBean.destroy")
        }

    }
}