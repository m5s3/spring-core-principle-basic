package hello.core

import hello.core.discount.DiscountPolicy
import hello.core.discount.FixDiscountPolicy
import hello.core.member.*
import hello.core.order.OrderService
import hello.core.order.OrderServiceImpl

fun main() {
    val memberRepository: MemberRepository = MemoryMemberRepository()
    val discountPolicy: DiscountPolicy = FixDiscountPolicy()
    val orderService: OrderService = OrderServiceImpl(memberRepository, discountPolicy)
    val memberService: MemberService = MemberServiceImpl(memberRepository)

    val memberId = 1L
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId = memberId, itemName = "itemA", 10000)

    println("order = $order")
    println("order.calculatePrice() = ${order.calculatePrice()}")
}