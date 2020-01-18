package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var boll = 0
        var score = 0

        (1..10).forEach {
            val fs = valueOf(pins, boll)

            score += fs.score()
            boll += fs.next()
        }

        return score
    }

    companion object {
        fun valueOf(pins: Array<Int>, boll: Int): FrameScore = when {
            pins[boll] == 10 -> FrameScore(FrameScoreType.STRIKE, pins, boll)
            pins[boll] + pins[boll + 1] == 10 -> FrameScore(FrameScoreType.SPARE, pins, boll)
            else -> FrameScore(FrameScoreType.ELSE, pins, boll)
        }
    }

    class FrameScore(private val type: FrameScoreType, private val pins: Array<Int>, private val boll: Int) {
        fun score(): Int = type.score(pins, boll)

        fun next(): Int = type.next(pins, boll)
    }

    enum class FrameScoreType {
        STRIKE {
            override fun score(pins: Array<Int>, boll: Int): Int = 10 + pins[boll + 1] + pins[boll + 2]
            override fun next(pins: Array<Int>, boll: Int): Int = 1
        },
        SPARE {
            override fun score(pins: Array<Int>, boll: Int): Int = 10 + pins[boll + 2]
            override fun next(pins: Array<Int>, boll: Int): Int = 2
        },
        ELSE {
            override fun score(pins: Array<Int>, boll: Int): Int = pins[boll] + pins[boll + 1]
            override fun next(pins: Array<Int>, boll: Int): Int = 2
        };

        abstract fun score(pins: Array<Int>, boll: Int): Int
        abstract fun next(pins: Array<Int>, boll: Int): Int


    }
}