package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = score(this.pins.toList(), 10)

    companion object {
        fun score(pins: List<Int>, frame: Int): Int {
            var strike = { e: Int -> strike(pins, e) }
            var spare = { e: Int -> spare(pins, e) }

            var nextTwoBoll = { e: Int -> pins[e + 1] + pins[e + 2] }
            var nextOneBoll = { e: Int -> pins[e + 2] }
            var frameScore = { e: Int -> pins[e] + pins[e + 1] }

            var score = 0
            var boll = 0

            repeat(frame) {
                when {
                    strike(boll) -> {
                        score += 10 + nextTwoBoll(boll)
                        boll += 1
                    }
                    spare(boll) -> {
                        score += 10 + nextOneBoll(boll)
                        boll += 2
                    }
                    else -> {
                        score += frameScore(boll)
                        boll += 2
                    }
                }
            }

            return score
        }

        fun strike(pins: List<Int>, boll: Int): Boolean = pins[boll] == 10
        fun spare(pins: List<Int>, boll: Int): Boolean = pins[boll] + pins[boll + 1] == 10
    }
}
