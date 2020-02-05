package com.kry.structure

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0;

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var boll = 0

        var score = 0
        repeat(10) {
            if (strike(boll)) {
                score += 10 + nextTwoBollForStrike(boll)
                boll += 1
            } else if (spare(boll)) {
                score += 10 + nextOneBollForSpare(boll)
                boll += 2
            } else {
                score += scoreInFrame(boll)
                boll += 2
            }
        }

        return score
    }

    private fun strike(boll: Int) = this.pins[boll] == 10
    private fun nextTwoBollForStrike(boll: Int) = this.pins[boll + 1] + this.pins[boll + 2]

    private fun spare(boll: Int) = this.pins[boll] + this.pins[boll + 1] == 10
    private fun nextOneBollForSpare(boll: Int) = this.pins[boll + 2]

    private fun scoreInFrame(boll: Int) = this.pins[boll] + this.pins[boll + 1]
}
