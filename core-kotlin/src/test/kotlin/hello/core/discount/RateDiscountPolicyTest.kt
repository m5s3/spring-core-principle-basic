package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RateDiscountPolicyTest {

    private val discountPolicy: DiscountPolicy = RateDiscountPolicy()

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    fun vip_o() {
        // Given
        val member: Member = Member(id = 1L, name = "memberVIP", grade = Grade.VIP)
        // When
        val discount = discountPolicy.discount(member, 10000)
        // Then
        assertThat(discount).isEqualTo(1000)
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되면 안된다")
    fun vip_x() {
        // Given
        val member: Member = Member(id = 2L, name = "memberBASIC", grade = Grade.BASIC)
        // When
        val discount = discountPolicy.discount(member, 10000)
        // Then
        assertThat(discount).isEqualTo(0)
    }
}