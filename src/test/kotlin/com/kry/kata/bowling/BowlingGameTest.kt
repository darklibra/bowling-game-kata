package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BowlingGameTest {
    private val g: BowlingGame

    init {
        g = BowlingGame()
    }

    @Nested
    inner class `프레임 전체 점수가 같은 테스트`() {
        @Test
        fun `점수 = 0`() {
            20.times { g.roll(0) }

            g.assertScore(0)
        }

        @Test
        fun `점수 = 1`() {
            20.times { g.roll(1) }

            g.assertScore(20)
        }

        @Test
        fun `점수 = 10`() {
            12.times { g.roll(10) }

            g.assertScore(300)
        }
    }

    @Test
    fun `스페어`() {
        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore((7 + 3) + 3 + 3)
    }

    @Nested
    inner class `스트라이크 테스트` {
        @Test
        fun `단일 스트라이크`() {
            g.roll(10)
            g.roll(3)
            g.roll(3)

            16.times { g.roll(0) }

            g.assertScore((10 + 3 + 3) + 3 + 3)
        }

        @Test
        fun `더블 스트라이크`() {
            g.roll(10)
            g.roll(10)
            g.roll(3)
            g.roll(3)

            16.times { g.roll(0) }

            g.assertScore((10 + 10 + 3) + (10 + 3 + 3) + 3 + 3)
        }
    }

    fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, this.score())
    fun Int.times(e: () -> Unit) = (1..this).forEach { e() }
}