package hello.core

import hello.core.member.*

fun main() {
    val memberRepository: MemberRepository = MemoryMemberRepository();
    val memberService: MemberService = MemberServiceImpl(memberRepository)
    val member = Member(1L, "memberA", Grade.VIP);
    memberService.join(member)

    val findMember = memberService.findMember(1L)
    println("new member = ${member.name}")
    println("findMember =  ${findMember!!.name}")
}