package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins;
    }

    fun score(): Int {
        return (1..10).fold(Pair(0, 0)) { (boll, accScore), _ ->
            var score = when {
                pins.strike(boll) -> 10 + pins.scoreForStrike(boll)
                pins.spare(boll) -> 10 + pins.scoreForSpare(boll)
                else -> pins.score(boll)
            }

            var nextBoll = if (pins.strike(boll)) 1 else 2

            Pair(boll + nextBoll, accScore + score)
        }.second
    }

    private fun Array<Int>.strike(boll: Int): Boolean = this[boll] == 10
    private fun Array<Int>.scoreForStrike(boll: Int): Int = this[boll + 1] + this[boll + 2]

    private fun Array<Int>.spare(boll: Int): Boolean = (this[boll] + this[boll + 1]) == 10
    private fun Array<Int>.scoreForSpare(boll: Int): Int = this[boll + 2]

    private fun Array<Int>.score(boll: Int): Int = this[boll] + this[boll + 1]
}