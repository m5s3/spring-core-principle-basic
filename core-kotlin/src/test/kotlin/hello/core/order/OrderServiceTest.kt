package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderServiceTest {
    private val memberRepository = MemoryMemberRepository()
    private val memberService = MemberServiceImpl(memberRepository)
    private val discountPolicy: DiscountPolicy = FixDiscountPolicy()
    private val orderService = OrderServiceImpl(memberRepository, discountPolicy)

    @Test
    fun createOrder() {
        // Given
        val memberId = 1L
        val member = Member(memberId, "memberA", Grade.VIP)
        memberService.join(member)

        // When
        val order: Order = orderService.createOrder(memberId, itemName = "itemA", 10000)

        // Then
        Assertions.assertThat(order.discountPrice).isEqualTo(1000)
    }
}