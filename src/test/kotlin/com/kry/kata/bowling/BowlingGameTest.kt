package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BowlingGameTest {
    var g = BowlingGame()

    @Test
    fun `전체 프레임 점수가 0인 테스트`() {
        20.times { g.roll(0) }

        g.assertScore(0)
    }

    @Test
    fun `전체 프레임 점수가 1인 테스트`() {
        20.times { g.roll(1) }

        g.assertScore(20)
    }

    @Test
    fun `스페어`() {
        g.roll(5)
        g.roll(5)
        g.roll(5)

        17.times { g.roll(0) }

        g.assertScore((5 + 5 + 5) + 5)
    }

    @Test
    fun `스트라이크`() {
        g.roll(10)
        g.roll(5)
        g.roll(5)

        16.times { g.roll(0) }

        g.assertScore((10 + 5 + 5) + 5 + 5)
    }

    @Test
    fun `퍼펙트 게임`() {
        12.times { g.roll(10) }

        g.assertScore(300)
    }

    fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
    fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}