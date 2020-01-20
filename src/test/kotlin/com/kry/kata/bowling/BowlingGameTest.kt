package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BowlingGameTest {
    @Test
    fun `전체 스코어 0인 테스트`() {
        val g = BowlingGame()

        20.times { g.roll(0) }

        g.assertScore(0)

    }

    @Test
    fun `전체 스코어 1인 테스트`() {
        val g = BowlingGame()

        20.times { g.roll(1) }

        g.assertScore(20)
    }

    @Test
    fun `단일 스페어 테스트`() {
        val g = BowlingGame()

        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore((7 + 3 + 3) + 3)
    }

    @Test
    fun `단일 스트라이크 테스트`() {
        val g = BowlingGame()

        g.roll(10)
        g.roll(3)
        g.roll(3)

        16.times { g.roll(0) }

        g.assertScore((10 + 3 + 3) + 3 + 3)
    }

    @Test
    fun `퍼펙트 게임 테스트`() {
        val g = BowlingGame()

        12.times { g.roll(10) }

        g.assertScore(300)
    }

    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
    private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}