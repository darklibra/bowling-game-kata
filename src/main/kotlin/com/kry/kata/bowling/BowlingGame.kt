package com.kry.kata.bowling

class BowlingGame {
    var pins = Array(21) { 0 }
    var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        return score(this.pins)
    }

    companion object Calculator {
        private fun score(pins: Array<Int>): Int {
            var score = 0
            var boll = 0

            (1..10).forEach {
                score += when {
                    pins.strike(boll) -> pins.scoreForStrike(boll)
                    pins.spare(boll) -> pins.scoreForSpare(boll)
                    else -> pins.scoreForFrame(boll)
                }

                boll += if (pins.strike(boll)) 1 else 2
            }

            return score
        }

        private fun Array<Int>.strike(boll: Int) = (this[boll] == 10)
        private fun Array<Int>.spare(boll: Int) = (this[boll] + this[boll + 1] == 10)

        private fun Array<Int>.scoreForStrike(boll: Int) = 10 + this[boll + 1] + this[boll + 2]
        private fun Array<Int>.scoreForSpare(boll: Int) = 10 + this[boll + 2]
        private fun Array<Int>.scoreForFrame(boll: Int) = this[boll] + this[boll + 1]
    }
}
