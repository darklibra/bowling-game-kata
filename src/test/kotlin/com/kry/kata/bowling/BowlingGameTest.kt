package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BowlingGameTest {
    private lateinit var g: BowlingGame

    @BeforeEach
    internal fun setUp() {
        g = BowlingGame()
    }

    @ParameterizedTest(name = "times => {0}, pins => {1}, rest => {2}, expected => {3}")
    @DisplayName("볼림 점수 테스트")
    @MethodSource("scoreTestArgs")
    fun sourceTest(times: Int, pins: List<Int>, rest: Int, expected: Int) {
        pins.forEach { g.roll(it) }

        (times - pins.size).times { g.roll(rest) }

        g.assertScore(expected)
    }

    private companion object {
        @JvmStatic
        fun scoreTestArgs() = Stream.of(
            Arguments.of(20, listOf(0), 0, 0),
            Arguments.of(20, listOf(1), 1, 20),
            Arguments.of(20, listOf(7, 3, 3), 0, ((7 + 3 + 3) + 3)),
            Arguments.of(20, listOf(10, 3, 3), 0, ((10 + 3 + 3) + 3 + 3)),
            Arguments.of(12, 12.seqMap { 10 }, 0, 300)
        )

        private fun Int.seqMap(fn: (Int) -> Int) = (1..this).map { fn(it) }
    }

    private fun Int.times(fn: () -> Unit) = (1..this).forEach { fn() }
    private fun BowlingGame.assertScore(expected: Int) = Assertions.assertEquals(expected, score())
}