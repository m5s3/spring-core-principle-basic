package hello.core.member

import hello.core.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MemberServiceTest {
    private lateinit var memberService: MemberService

    @BeforeEach
    fun beforeEach() {
        val appConfig = AppConfig()
        memberService = appConfig.memberService()
    }

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