package com.kry.kata.bowling

typealias Score = Array<Int>

class BowlingGame {
    private var pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var score = 0
        var boll = 0

        for (i in 1..10) {
            val type = FrameScoreType.decide(pins, boll)
            score += type.score(pins, boll)
            boll += type.move
        }

        return score
    }

    enum class FrameScoreType(val move: Int) {
        STRIKE(1) {
            override fun score(pins: Score, boll: Int): Int = 10 + pins[boll + 1] + pins[boll + 2]
        },
        SPARE(2) {
            override fun score(pins: Score, boll: Int): Int = 10 + pins[boll + 2]
        },
        ELSE(2) {
            override fun score(pins: Score, boll: Int): Int = pins[boll] + pins[boll + 1]
        };

        abstract fun score(pins: Score, boll: Int): Int

        companion object {
            fun decide(pins: Score, boll: Int): FrameScoreType {
                return when {
                    pins[boll] == 10 -> STRIKE
                    pins[boll] + pins[boll + 1] == 10 -> SPARE
                    else -> ELSE
                }
            }
        }
    }
}
