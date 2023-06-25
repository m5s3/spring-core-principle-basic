package hello.core.order

import hello.core.discount.DiscountPolicy
import hello.core.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
    @Autowired
    private val memberRepository: MemberRepository,
    @Autowired
    private val discountPolicy: DiscountPolicy,
) : OrderService {
    override fun createOrder(memberId: Long, itemName: String, itemPrice: Int): Order {
        val member = memberRepository.findById(memberId)
        val discountPrice = discountPolicy.discount(member!!, itemPrice)
        return Order(memberId, itemName, itemPrice, discountPrice)
    }
}