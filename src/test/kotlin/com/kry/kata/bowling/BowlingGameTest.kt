package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BowlingGameTest {
    private lateinit var g: BowlingGame

    @BeforeEach
    internal fun setUp() {
        g = BowlingGame()
    }

    @Nested
    inner class `전체 스코어 점수가 같은 테스트 그룹` {
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
    }

    @Nested
    inner class `스페어 테스트 그룹` {
        @Test
        fun `단일 스페어 테스트`() {
            g.roll(7)
            g.roll(3)
            g.roll(3)

            17.times { g.roll(0) }

            g.assertScore(16)
        }
    }

    @Nested
    inner class `스트라이크 테스트 그룹` {
        @Test
        fun `단일 스트라이크 테스트`() {
            g.roll(10)
            g.roll(3)
            g.roll(3)

            16.times { g.roll(0) }

            g.assertScore(22)
        }
    }
}

private fun Int.times(fn: () -> Unit) = repeat(this) { fn() }
private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
