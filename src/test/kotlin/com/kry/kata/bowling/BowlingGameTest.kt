package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BowlingGameTest {
    val g = BowlingGame();

    @Nested
    inner class `전체 프레임 점수가 같은 경우 테스트`() {
        @Test
        fun `0`() {
            20.times { g.roll(0) }

            g.assertScore(0)
        }

        @Test
        fun `1`() {
            20.times { g.roll(1) }

            g.assertScore(20)
        }

        @Test
        fun `10`() {
            12.times { g.roll(10) }

            g.assertScore(300)
        }
    }

    @Test
    fun `spare`() {
        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore((7 + 3) + 3 + 3)
    }

    @Test
    fun `strike`() {
        g.roll(10)
        g.roll(3)
        g.roll(3)

        16.times { g.roll(0) }

        g.assertScore((10 + 3 + 3) + 3 + 3)
    }

    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
    private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}