package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = BowlingGameScore(this.pins.toList()).score
}

private class BowlingGameScore(pins: List<Int>) {
    val score by lazy {
        (1..10).fold(Pair(0, 0)) { (boll, score), _ ->
            val strike = pins[boll] == 10
            val fs = pins[boll] + pins[boll + 1]

            val spare = fs == 10

            when {
                strike -> Pair(boll + 1, score + 10 + nextTwoBoll(pins, boll))
                spare -> Pair(boll + 2, score + 10 + nextOneBoll(pins, boll))
                else -> Pair(boll + 2, score + fs)
            }
        }.second
    }

    private fun nextTwoBoll(pins: List<Int>, boll: Int): Int = pins[boll + 1] + pins[boll + 2]
    private fun nextOneBoll(pins: List<Int>, boll: Int): Int = pins[boll + 2]
}