package hello.core

import hello.core.member.Grade
import hello.core.member.Member
import hello.core.member.MemberServiceImpl
import hello.core.member.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MemberServiceTest {
    private val memberRepository = MemoryMemberRepository()
    private val memberService = MemberServiceImpl(memberRepository)

    @Test
    fun join() {
        // Given
        val member = Member(1L, "memberA", Grade.VIP)

        // When
        memberService.join(member)
        val findMember = memberService.findMember(1L)

        // Then
        Assertions.assertThat(member).isEqualTo(findMember)
    }
}