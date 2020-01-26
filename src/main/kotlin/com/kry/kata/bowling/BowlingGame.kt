package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var score = 0
        var boll = 0

        (1..10).forEach {
            if (strike(boll)) {
                score += 10 + nextTwoBollForStrike(boll)
                boll += 1
            } else if (spare(boll)) {
                score += 10 + nextOneBollForSpare(boll)
                boll += 2
            } else {
                score += score(boll)
                boll += 2
            }
        }

        return score
    }

    private fun strike(boll: Int): Boolean = this.pins[boll] == 10
    private fun nextTwoBollForStrike(boll: Int): Int = this.pins[boll + 1] + this.pins[boll + 2]

    private fun spare(boll: Int) = this.pins[boll] + this.pins[boll + 1] == 10
    private fun nextOneBollForSpare(boll: Int): Int = this.pins[boll + 2]

    private fun score(boll: Int) = this.pins[boll] + this.pins[boll + 1]
}
