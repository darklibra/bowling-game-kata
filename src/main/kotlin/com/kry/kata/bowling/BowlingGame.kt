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

        repeat(10) {
            if (pins.strike(boll)) {
                score += 10 + pins.nextTwoBollForStrike(boll)
                boll += 1
            } else if (pins.spare(boll)) {
                score += 10 + pins.nextOneBollForSpare(boll)
                boll += 2
            } else {
                score += pins.score(boll)
                boll += 2
            }
        }

        return score
    }
}

private fun Array<Int>.strike(boll: Int): Boolean = this[boll] == 10
private fun Array<Int>.nextTwoBollForStrike(boll: Int): Int = this[boll + 1] + this[boll + 2]

private fun Array<Int>.spare(boll: Int): Boolean = this[boll] + this[boll + 1] == 10
private fun Array<Int>.nextOneBollForSpare(boll: Int): Int = this[boll + 2]

private fun Array<Int>.score(boll: Int): Int = this[boll] + this[boll + 1]
