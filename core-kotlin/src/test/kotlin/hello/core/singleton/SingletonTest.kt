package hello.core.singleton

import hello.core.AppConfig
import hello.core.member.MemberService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {

    @Test
    @DisplayName("스프링이 없는 순수한 컨테이너")
    fun pureContainer() {
        val appConfig = AppConfig();

        val memberService1 = appConfig.memberService()
        val memberService2 = appConfig.memberService()

        println("memberService1 = $memberService1")
        println("memberService2 = $memberService2")

        assertThat(memberService1).isNotSameAs(memberService2)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    fun springContainer() {
        val applicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService1 = applicationContext.getBean("memberService", MemberService::class.java)
        val memberService2 = applicationContext.getBean("memberService", MemberService::class.java)

        assertThat(memberService1).isSameAs(memberService2)
    }
}