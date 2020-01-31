package com.kry.kata.bowling

class BowlingGame {
  private val pins = Array(21) { 0 }
  private var num = 0

  fun roll(pins: Int) {
    this.pins[num++] = pins
  }

  fun score(): Int = score(10, pins.toList())

  companion object {
    private fun score(frame: Int, pins: List<Int>): Int {
      var score = 0
      var boll = 0

      fun strike(): Boolean = pins[boll] == 10
      fun spare(): Boolean = pins[boll] + pins[boll + 1] == 10

      fun scoreForStrike(): Int = 10 + pins[boll + 1] + pins[boll + 2]
      fun scoreForSpare(): Int = 10 + pins[boll + 2]
      fun score(): Int = pins[boll] + pins[boll + 1]

      repeat(frame) {
        when {
          strike() -> {
            score += scoreForStrike()
            boll += 1
          }
          spare() -> {
            score += scoreForSpare()
            boll += 2
          }
          else -> {
            score += score()
            boll += 2
          }
        }
      }

      return score
    }
  }
}
