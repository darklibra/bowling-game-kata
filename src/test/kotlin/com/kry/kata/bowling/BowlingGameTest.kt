package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BowlingGameTest {
    lateinit var g: BowlingGame

    @BeforeEach
    fun setUp() {
        this.g = BowlingGame()
    }

    @Test
    fun `전체 점수 0인 테스트`() {
        val g = BowlingGame()

        20.times { g.roll(0) }

        g.assertScore(0)
    }

    @Test
    fun `전체 점수 1인 테스트`() {
        val g = BowlingGame()

        20.times { g.roll(1) }

        g.assertScore(20)
    }

    @Test
    fun `spare test`() {
        val g = BowlingGame()

        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore((7 + 3 + 3) + 3)
    }

    @Test
    fun `strike test`() {
        val g = BowlingGame()

        g.roll(10)
        g.roll(3)
        g.roll(3)

        16.times { g.roll(0) }

        g.assertScore((10 + 3 + 3) + 3 + 3)
    }

    @Test
    fun `perfect game test`() {
        val g = BowlingGame()

        12.times { g.roll(10) }

        g.assertScore(300)
    }

    fun Int.times(fn: () -> Unit) = repeat(this) { fn() }
    fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}