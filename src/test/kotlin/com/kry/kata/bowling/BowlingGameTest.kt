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
    fun `all zero`() {
        20.times { g.roll(0) }

        g.assertScore(0)
    }

    @Test
    fun `all ont`() {
        20.times { g.roll(1) }

        g.assertScore(20)
    }

    @Test
    fun `spare`() {
        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore((7 + 3 + 3) + 3)
    }

    @Test
    fun `strike`() {
        g.roll(10)
        g.roll(3)
        g.roll(3)

        16.times { g.roll(0) }

        g.assertScore((10 + 3 + 3) + 3 + 3)
    }

    @Test
    fun `perfect game`() {
        12.times { g.roll(10) }

        g.assertScore(300)
    }

    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
    private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}