package hello.core.discount

import hello.core.member.Grade
import hello.core.member.Member

class RateDiscountPolicy : DiscountPolicy {
    val discountPercent = 10
    override fun discount(member: Member, price: Int): Int {
        if (member.grade === Grade.VIP) {
            return price * discountPercent / 100
        }
        return 0
    }
}