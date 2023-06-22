package hello.core.findBean

import hello.core.member.MemberRepository
import hello.core.member.MemoryMemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.beans.factory.getBeansOfType
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest {

    private val ac = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

    @Test
    @DisplayName("타입으로 조회시 같은 값이 둘이면, 중복 오류가 발생한다")
    fun findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
            val bean = ac.getBean(MemberRepository::class.java)
        }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상이면, 이름으로 조회하면 된다")
    fun findBeanByName() {
        val bean = ac.getBean("memberRepository1", MemberRepository::class.java)
        assertThat(bean).isInstanceOf(MemberRepository::class.java)
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    fun findAllBeanByType() {
        val beansOfType = ac.getBeansOfType(MemberRepository::class.java)
        for (mutableEntry in beansOfType) {
            println("mutableEntry KEY = ${mutableEntry.key}")
            println("mutableEntry VALUE = ${mutableEntry.value}")
        }
        println(beansOfType)
    }




    @Configuration
    class SameBeanConfig {

        @Bean
        fun memberRepository1(): MemberRepository {
            return MemoryMemberRepository();
        }

        @Bean
        fun memberRepository2(): MemberRepository {
            return MemoryMemberRepository();
        }
    }
}