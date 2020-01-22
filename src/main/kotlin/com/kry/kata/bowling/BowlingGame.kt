package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = Calculator(this.pins).cal()

    class Calculator(pins: Array<Int>) {
        private val snapshot = pins.toList()

        fun cal(): Int {
            var boll = 0
            var score = 0

            (1..10).forEach {
                if (strike(boll)) {
                    score += 10 + nextTwoBoll(boll)
                    boll += 1
                } else if (spare(boll)) {
                    score += 10 + nextOneBoll(boll)
                    boll += 2
                } else {
                    score += frameScore(boll)
                    boll += 2
                }
            }

            return score
        }

        private fun spare(boll: Int) = (snapshot[boll] + snapshot[boll + 1]) == 10
        private fun strike(boll: Int) = snapshot[boll] == 10
        private fun frameScore(boll: Int) = snapshot[boll] + snapshot[boll + 1]
        private fun nextTwoBoll(boll: Int): Int = snapshot[boll + 1] + snapshot[boll + 2]
        private fun nextOneBoll(boll: Int): Int = snapshot[boll + 2]
    }
}
