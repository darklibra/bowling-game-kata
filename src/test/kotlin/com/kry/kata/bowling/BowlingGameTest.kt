package com.kry.kata.bowling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BowlingGameTest {
  val g = BowlingGame()

  @Nested
  inner class `전체 프레임 점수가 같은 경우` {
    @ParameterizedTest(name = "{index} => pins: {0}, expected: {1}")
    @CsvSource(value = ["0, 0", "1, 20"])
    fun `스페어 & 스트라이크가 없는 같은 값 테스트`(pins: Int, expected: Int) {
      20.times { g.roll(pins) }

      Assertions.assertEquals(expected, g.score())
    }

    @Test
    fun `전체 점수가 5인 테스트`() {
      21.times { g.roll(5) }

      Assertions.assertEquals(150, g.score())
    }

    @Test
    fun `퍼펙트 게임 테스트`() {
      12.times { g.roll(10) }

      Assertions.assertEquals(300, g.score())
    }
  }

  @Test
  fun `스페어 테스트`() {
    g.roll(3)
    g.roll(7)
    g.roll(3)

    17.times { g.roll(0) }

    Assertions.assertEquals(16, g.score())
  }

  @Test
  fun `스트라이크 테스트`() {
    g.roll(10)
    g.roll(5)
    g.roll(3)

    16.times { g.roll(0) }

    Assertions.assertEquals(26, g.score())
  }

  fun Int.times(fn: () -> Unit) = (1..this).forEach { _ -> fn() }
}
