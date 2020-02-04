package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        var pins = this.pins.toList()
        var boll = 0

        val frames = object : Frames {
            override fun strike(): Boolean = pins[boll] == 10

            override fun appendScoreForStrike(): Int = pins[boll + 1] + pins[boll + 2]

            override fun moveForStrike() {
                boll += 1
            }

            override fun appendScoreForSpare(): Int = pins[boll + 2]

            override fun scoreForFrame(): Int = pins[boll] + pins[boll + 1]

            override fun move() {
                boll += 2
            }
        }

        return cal(frames)
    }

    private fun cal(frames: Frames): Int {
        var score = 0

        repeat(10) r@{
            if (frames.strike()) {
                score += 10 + frames.appendScoreForStrike()
                frames.moveForStrike()
                return@r
            }

            val s = frames.scoreForFrame()
            score += s

            if (s == 10) score += frames.appendScoreForSpare()
            frames.move()
        }

        return score
    }

    interface Frames {
        fun strike(): Boolean

        fun appendScoreForStrike(): Int

        fun moveForStrike()

        fun appendScoreForSpare(): Int

        fun scoreForFrame(): Int

        fun move()
    }
}
