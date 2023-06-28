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

class SingletonWithPrototype {
    @Test
    fun singletonClientUsePrototype() {
        val ac = AnnotationConfigApplicationContext(ClientBean::class.java, PrototypeBean::class.java)
        val clientBean1 = ac.getBean(ClientBean::class.java)
        println("clientBean1 = $clientBean1")
        val count1: Int = clientBean1.logic()
        val clientBean2 = ac.getBean(ClientBean::class.java)
        println("count1 = ${count1}")
        println("clientBean2 = $clientBean2")
//        clientBean2.logic()
//        println("clientBean2.logic() = ${clientBean2.logic()}")
//        val count2: Int = clientBean2.logic()

        assertThat(count1).isEqualTo(1)
//        assertThat(count2).isEqualTo(1)
    }

    @Scope(SCOPE_SINGLETON)
    class ClientBean(
        @Autowired
        private var prototypeBeanProvider: ObjectProvider<PrototypeBean>,
    ) {
        fun logic(): Int {
            val prototypeBean = prototypeBeanProvider.getObject()
            prototypeBean.addCount()
            return prototypeBean.count
        }
    }

    @Scope(SCOPE_PROTOTYPE)
    class PrototypeBean(
        var count:Int = 0,
    ) {

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