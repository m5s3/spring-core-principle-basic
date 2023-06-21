package hello.core

import hello.core.discount.RateDiscountPolicy
import hello.core.member.MemberService
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    @Bean
    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), rateDiscountPolicy())

    @Bean
    fun rateDiscountPolicy() = RateDiscountPolicy()

    @Bean
    fun memberRepository() = MemoryMemberRepository()
}