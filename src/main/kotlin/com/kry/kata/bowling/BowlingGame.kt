package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }

    private val writer = ScoreWriter(pins)

    fun roll(pins: Int) {
        this.writer.write(pins)
    }

    fun score(): Int {
        return ScoreCal(pins).cal()
    }

    inner class ScoreWriter(val pins: Array<Int>) {
        private var num = 0

        fun write(pins: Int) {
            if (isLast()) {
                this.pins[num++] = pins
            } else {
                this.pins[num] = pins
                num += if (pins == 10) 2 else 1
            }
        }

        private fun isLast(): Boolean {
            return num >= (19 - 1)
        }
    }

    inner class ScoreCal(val pins: Array<Int>) {
        fun cal(): Int {
            var score = 0
            var boll = 0

            (1..10).forEach {
                val strike = pins[boll] == 10
                val fs = pins[boll] + pins[boll + 1]

                if (strike) {
                    score += fs + nextTwoBollForStrike(boll)
                } else if (pins[boll] + pins[boll + 1] == 10) {
                    score += fs + pins[boll + 2]
                } else {
                    score += fs
                }

                boll += 2
            }

            return score
        }

        private fun nextTwoBollForStrike(boll: Int): Int {
            val fb = pins[boll + 2]

            return when {
                pins.lastIndex <= boll + 3 -> fb
                fb == 10 -> fb + pins[boll + 4]
                else -> pins[boll + 2] + pins[boll + 3]
            }
        }
    }
}
