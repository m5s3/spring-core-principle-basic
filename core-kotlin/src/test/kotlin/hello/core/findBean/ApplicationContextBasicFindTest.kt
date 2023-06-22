package hello.core.findBean

import hello.core.AppConfig
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest {
    private val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

    @Test
    @DisplayName("빈 이름으로 조회")
    fun findBeanByName() {
        // Given
        val memberService = ac.getBean("memberService", MemberService::class.java)

        // When & Then
        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("이름 없이 타임으로 조회")
    fun findBeanByType() {
        // Given
        val memberService = ac.getBean(MemberService::class.java)

        // When & Then
        assertThat(memberService).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    fun findBeanByName2() {
        // Given
        val memberServiceImpl = ac.getBean("memberService", MemberServiceImpl::class.java)

        // When & Then
        assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl::class.java)
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    fun findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException::class.java) {
            ac.getBean("xxxxx", MemberService::class.java)
        }
    }

}