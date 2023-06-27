package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member
import org.springframework.stereotype.Component

@Component
class FixDiscountPolicy : DiscountPolicy {

    val discountFixAmount: Int = 1000
    override fun discount(member: Member, price: Int): Int {
        if (member.grade === Grade.VIP) {
            return discountFixAmount
        }
        return 0
    }
}