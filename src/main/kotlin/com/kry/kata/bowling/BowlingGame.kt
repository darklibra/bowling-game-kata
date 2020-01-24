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
                score += 10 + this.pins[boll + 1] + this.pins[boll + 2]
                boll += 1
            } else if (spare(boll)) {
                score += 10 + this.pins[boll + 2]
                boll += 2
            } else {
                score += this.pins[boll] + this.pins[boll + 1]
                boll += 2
            }

        }

        return score
    }

    private fun strike(boll: Int) = this.pins[boll] == 10

    private fun spare(boll: Int) = this.pins[boll] + this.pins[boll + 1] == 10
}
