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
    fun `전체 점수 0인 테스트`() {
        20.times { g.roll(0) }

        g.assertScore(0)
    }

    @Test
    fun `전체 점수 1인 테스트`() {
        20.times { g.roll(1) }

        g.assertScore(20)
    }

    @Test
    fun `스페어 테스트`() {
        g.roll(7)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore(16)
    }

    @Test
    fun `스트라이크 테스트`() {
        g.roll(10)
        g.roll(3)
        g.roll(3)

        17.times { g.roll(0) }

        g.assertScore(22)
    }

    @Test
    fun `퍼펙트 게임 테스트`() {
        12.times { g.roll(10) }

        g.assertScore(300)
    }

    private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
}