package hello.core.autowired

import hello.core.AutoAppConfig
import hello.core.discount.DiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeansTest {

    @Test
    fun allBeans() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)

        val discountService: DiscountService = ac.getBean(DiscountService::class.java)
        val member = Member(1L, "memberA", Grade.VIP)
        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        assertThat(discountService).isInstanceOf(DiscountService::class.java)
        assertThat(discountPrice).isEqualTo(1000)

        val rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy")
        assertThat(rateDiscountPrice).isEqualTo(2000)

    }

    class DiscountService(
        @Autowired
        private val policyMap: Map<String, DiscountPolicy> = mapOf(),
        @Autowired
        private val policies: List<DiscountPolicy> = listOf(),
    ) {
        init {
            println("policyMap = ${policyMap}")
            println("policies = ${policies}")
        }

        fun discount(member: Member, price: Int, discountCode: String): Int {
            val discountPolicy: DiscountPolicy? = this.policyMap[discountCode]
            return discountPolicy!!.discount(member, price)
        }
    }
}