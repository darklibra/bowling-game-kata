package com.kry.kata.bowling

import com.kry.kata.bowling.BowlingGame.BowlingGame.strike

private typealias Score = Array<Int>

class BowlingGame {
    var pins = Score(21) { 0 }
    var num = 0;

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = BowlingGame.cal(this.pins)

    object BowlingGame {
        fun cal(pins: Score): Int {
            var boll = 0
            var score = 0

            (1..10).forEach {
                if (pins.strike(boll)) {
                    score += pins.scoreForStrike(boll)
                    boll += 1
                } else if (pins.spare(boll)) {
                    score += pins.scoreForSpare(boll)
                    boll += 2
                } else {
                    score += pins.scoreForFrame(boll)
                    boll += 2
                }
            }

            return score
        }

        private fun Score.strike(boll: Int): Boolean = this[boll] == 10
        private fun Score.spare(boll: Int): Boolean = scoreForFrame(boll) == 10
        private fun Score.scoreForStrike(boll: Int): Int = 10 + this[boll + 1] + this[boll + 2]
        private fun Score.scoreForSpare(boll: Int): Int = 10 + this[boll + 2]
        private fun Score.scoreForFrame(boll: Int): Int = this[boll] + this[boll + 1]
    }
}
