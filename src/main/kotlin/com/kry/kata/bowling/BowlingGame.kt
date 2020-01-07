package com.kry.kata.bowling

private typealias Score = Array<Int>

class BowlingGame {
  val pins: Score = Score(21) { 0 }
  var num = 0

  fun roll(pins: Int) {
    this.pins[num++] = pins
  }

  fun score(): Int {
    var score = 0
    var boll = 0

    (1..10).forEach { _ ->
      when {
        pins.strike(boll) -> {
          score += 10 + pins.nextTwoBoll(boll)
          boll += 1
        }
        pins.spare(boll) -> {
          score += 10 + pins.nextOneBoll(boll)
          boll += 2
        }
        else -> {
          score += pins.frameScore(boll)
          boll += 2
        }
      }
    }

    return score
  }

  private fun Score.strike(boll: Int): Boolean = this[boll] == 10
  private fun Score.spare(boll: Int): Boolean = (this[boll] + this[boll + 1]) == 10
  private fun Score.nextOneBoll(boll: Int): Int = this[boll + 2]
  private fun Score.nextTwoBoll(boll: Int): Int = this[boll + 1] + this[boll + 2]

  private fun Score.frameScore(boll: Int): Int = this[boll] + this[boll + 1]
}
