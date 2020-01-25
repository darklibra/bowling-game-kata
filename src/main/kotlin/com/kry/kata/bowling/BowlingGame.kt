package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        return (1..10).fold(Pair(0, 0)) { (boll, score), _ ->
            val p = when {
                strike(boll) -> Pair(1, 10 + nextTwoBollForStrike(boll))
                spare(boll) -> Pair(2, 10 + nextOneBollForSpare(boll))
                else -> Pair(2, this.pins[boll] + this.pins[boll + 1])
            }

            Pair(boll + p.first, score + p.second)
        }.second
    }

    private fun strike(boll: Int): Boolean = this.pins[boll] == 10
    private fun spare(boll: Int): Boolean = this.pins[boll] + this.pins[boll + 1] == 10

    private fun nextTwoBollForStrike(boll: Int): Int = this.pins[boll + 1] + this.pins[boll + 2]
    private fun nextOneBollForSpare(boll: Int): Int = this.pins[boll + 2]
}
