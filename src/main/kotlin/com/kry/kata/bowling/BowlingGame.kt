package com.kry.kata.bowling

class BowlingGame {
    private val score = Score()
    private val calculator = ScoreCalculator()

    fun roll(pins: Int) = score.store(pins)

    fun score(): Int = calculator.cal(score)

    class Score {
        private val pins = Array(21) { 0 }

        private var num = 0

        fun store(pins: Int) {
            this.pins[num++] = pins
        }

        fun strike(boll: Int): Boolean = pins[boll] == 10
        fun nextTwoBollForStrike(boll: Int): Int = pins[boll + 1] + pins[boll + 2]

        fun spare(boll: Int): Boolean = pins[boll] + pins[boll + 1] == 10
        fun nextOneBollForSpare(boll: Int): Int = pins[boll + 2]

        fun frameScore(boll: Int): Int = pins[boll] + pins[boll + 1]
    }

    class ScoreCalculator {
        fun cal(score: Score): Int {
            var boll = 0
            var ret = 0

            (1..10).forEach {
                val strike = score.strike(boll)

                ret += when {
                    strike -> 10 + score.nextTwoBollForStrike(boll)
                    score.spare(boll) -> 10 + score.nextOneBollForSpare(boll)
                    else -> score.frameScore(boll)
                }
                boll += if (strike) 1 else 2
            }

            return ret
        }
    }
}
