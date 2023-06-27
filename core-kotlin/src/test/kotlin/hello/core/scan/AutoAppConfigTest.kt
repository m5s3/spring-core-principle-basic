package hello.core.scan

import hello.core.AutoAppConfig
import hello.core.member.MemberRepository
import hello.core.member.MemberService
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest {

    @Test
    fun basicScan() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

        val memberService: MemberService = ac.getBean(MemberService::class.java)

        assertThat(memberService).isInstanceOf(MemberService::class.java)

        val orderServiceImpl: OrderServiceImpl = ac.getBean(OrderServiceImpl::class.java)

    }
}