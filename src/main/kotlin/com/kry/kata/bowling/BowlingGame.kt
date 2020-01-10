package com.kry.kata.bowling

private typealias BowlingScore = Array<Int>

class BowlingGame {
    var pins = BowlingScore(20) { 0 }
    var num = 0;

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var score = 0
        var roll = 0

        (1..10).forEach { _ ->
            score += when {
                pins.strike(roll) -> 10 + pins.nextTwoBollForStrike(roll)
                pins.spare(roll) -> 10 + pins.nextOneBollForSpare(roll)
                else -> pins.score(roll)
            }
            roll += if (pins.strike(roll)) 1 else 2
        }

        return score
    }

    private fun BowlingScore.spare(roll: Int) = score(roll) == 10
    private fun BowlingScore.strike(roll: Int) = this[roll] == 10

    private fun BowlingScore.nextTwoBollForStrike(roll: Int) = this[roll + 1] + this[roll + 2]
    private fun BowlingScore.nextOneBollForSpare(roll: Int) = this[roll + 2]
    private fun BowlingScore.score(roll: Int) = this[roll] + this[roll + 1]
}
