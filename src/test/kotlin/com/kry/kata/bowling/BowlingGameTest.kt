package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class BowlingGameTest {
    private lateinit var g: BowlingGame

    @BeforeEach
    internal fun setUp() {
        this.g = BowlingGame()
    }

    @Test
    fun `전체 프레임 점수가 0 테스트`() {
        many(20) { g.roll(0) }

        Assertions.assertEquals(0, g.score())
    }

    @Test
    fun `전체 프레임 점수가 1 테스트`() {
        many(20) { g.roll(1) }

        Assertions.assertEquals(20, g.score())
    }

    @Test
    fun `스페어 테스트`() {
        g.roll(3)
        g.roll(7)
        g.roll(3)

        many(17) { g.roll(0) }

        Assertions.assertEquals(16, g.score())
    }

    @Test
    fun `스트라이크 테스트`() {
        g.roll(10)
        g.roll(3)
        g.roll(3)

        many(16) { g.roll(0) }

        Assertions.assertEquals(22, g.score())
    }

    @Test
    fun `퍼펙트 게임 테스트`() {
        many(12) { g.roll(10) }

        Assertions.assertEquals(300, g.score())
    }

    fun many(times: Int, body: () -> Unit) {
        (1..times).forEach { _ -> body() }
    }
}