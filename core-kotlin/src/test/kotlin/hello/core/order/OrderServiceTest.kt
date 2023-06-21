package hello.core.order

import hello.core.AppConfig
import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.member.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {
    private lateinit var memberService: MemberService
    private lateinit var orderService: OrderService

    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
        orderService = appConfig.orderService()
    }

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