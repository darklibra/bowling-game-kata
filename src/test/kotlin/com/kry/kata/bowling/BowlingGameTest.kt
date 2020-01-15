package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BowlingGameTest {
    private lateinit var g: BowlingGame

    @BeforeEach
    internal fun setUp() {
        g = BowlingGame()
    }

    @Test
    fun `전체 점수 0 테스트`() {
        20.times { g.roll(0) }

        Assertions.assertEquals(0, g.score())
    }

    @Test
    fun `전체 점수 1 테스트`() {
        20.times { g.roll(1) }

        Assertions.assertEquals(20, g.score())
    }

    @Test
    fun `스페어`() {
        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        Assertions.assertEquals((7 + 3 + 3) + 3, g.score())
    }

    @Test
    fun `스트라이크`() {
        g.roll(10)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        Assertions.assertEquals((10 + 3 + 3) + 3 + 3, g.score())
    }

    @Test
    fun `퍼펙트 게임`() {
        12.times { g.roll(10) }

        Assertions.assertEquals(300, g.score())
    }

    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
}