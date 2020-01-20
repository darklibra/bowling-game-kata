package com.kry.kata.bowling

class BowlingGame {
    val pins = Array(21) { 0 }
    var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        val calculator = ScoreCalculator(this.pins)

        calculator.cal()

        return calculator.score
    }

    class ScoreCalculator(val pins: Array<Int>) {
        var score = 0
        var boll = 0

        fun cal() {
            (1..10).forEach {
                when {
                    strike() -> calStrike()
                    spare() -> calSpare()
                    else -> calFrame()
                }
            }
        }

        private fun calFrame() {
            score += pins[boll] + pins[boll + 1]
            boll += 2
        }

        private fun calSpare() {
            score += 10 + pins[boll + 2]
            boll += 2
        }

        private fun spare() = pins[boll] + pins[boll + 1] == 10

        private fun calStrike() {
            score += 10 + pins[boll + 1] + pins[boll + 2]
            boll += 1
        }

        private fun strike() = pins[boll] == 10
    }
}
