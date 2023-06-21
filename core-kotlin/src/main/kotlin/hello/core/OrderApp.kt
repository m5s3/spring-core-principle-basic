package hello.core

import hello.core.member.*
import hello.core.order.OrderService

fun main() {
    val appConfig = AppConfig()
    val orderService: OrderService = appConfig.orderService()
    val memberService: MemberService = appConfig.memberService()

    val memberId = 1L
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val order = orderService.createOrder(memberId = memberId, itemName = "itemA", 10000)

    println("order = $order")
    println("order.calculatePrice() = ${order.calculatePrice()}")
}