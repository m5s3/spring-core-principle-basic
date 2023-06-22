package hello.core.findBean

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.discount.RateDiscountPolicy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest {

    private val ac = AnnotationConfigApplicationContext(TestConfig::class.java)

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    fun findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException::class.java) {
            ac.getBean(DiscountPolicy::class.java)
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 이름으로 조회하면 된다.")
    fun findBeanByParentTypeByName() {
        val rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    fun findBeanBySubType() {
        val rateDiscountPolicy = ac.getBean(RateDiscountPolicy::class.java)
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy::class.java)
    }

    @Test
    @DisplayName("부모 타입으로 조회하기")
    fun findBeanByParentType() {
        val beansOfType = ac.getBeansOfType(DiscountPolicy::class.java)
        for (bean in beansOfType) {
            println("bean = ${bean.key}")
            println("bean = ${bean.value}")
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회하기 - Object")
    fun findBeanByObjectType() {
        val beansOfType = ac.getBeansOfType(Object::class.java)
        for (bean in beansOfType) {
            println("bean = ${bean.key}")
            println("bean = ${bean.value}")
        }
    }

    @Configuration
    class TestConfig {

        @Bean
        fun rateDiscountPolicy(): DiscountPolicy = RateDiscountPolicy()

        @Bean
        fun fixDiscountPolicy(): DiscountPolicy = FixDiscountPolicy()
    }
}