package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = ScoreCal(this.pins.toList()).score

    class ScoreCal(pins: List<Int>) {
        val score by lazy {
            var score = 0
            var boll = 0

            val strike = { strike(pins, boll) }
            val spare = { spare(pins, boll) }
            val nextTwoBoll = { pins[boll + 1] + pins[boll + 2] }
            val nextOneBoll = { pins[boll + 2] }
            val fs = { pins[boll] + pins[boll + 1] }

            repeat(10) r@{
                if (strike()) {
                    score += 10 + nextTwoBoll()
                    boll += 1
                    return@r
                }

                score += if (spare()) 10 + nextOneBoll() else fs()
                boll += 2
            }

            score
        }

        companion object {
            fun strike(pins: List<Int>, boll: Int): Boolean = pins[boll] == 10
            fun spare(pins: List<Int>, boll: Int): Boolean = pins[boll] + pins[boll + 1] == 10
        }
    }
}
