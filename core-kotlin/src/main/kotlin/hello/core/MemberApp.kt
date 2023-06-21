package hello.core

import hello.core.member.*

fun main() {
    val appConfig = AppConfig()
    val memberService: MemberService = appConfig.memberService()
    val member = Member(1L, "memberA", Grade.VIP)
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("findMember =  ${findMember!!.name}")
}