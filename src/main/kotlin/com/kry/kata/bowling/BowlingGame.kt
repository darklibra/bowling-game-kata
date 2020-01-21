package com.kry.kata.bowling

class BowlingGame {
    private val score = ScoreStore()

    fun roll(pins: Int) = this.score.roll(pins)

    fun score(): Int = ScoreCalculator(this.score).cal()

    class ScoreStore {
        private val pins = Array(21) { 0 }

        private var num = 0

        fun roll(pins: Int) {
            this.pins[num++] = pins
        }

        fun getPins(): List<Int> {
            return this.pins.toList()
        }
    }

    class ScoreCalculator(score: ScoreStore) {
        private val snapshot = score.getPins()

        fun cal(): Int {
            return cal(1, 0, 0)
        }

        private tailrec fun cal(frame: Int, boll: Int, acc: Int): Int {
            if (frame > 10) {
                return acc
            }

            val fs = when {
                strike(boll) -> Pair(1, calStrike(boll))
                spare(boll) -> Pair(2, calSpare(boll))
                else -> Pair(2, cal(boll))
            }

            return cal(frame + 1, boll + fs.first, acc + fs.second)
        }

        private fun strike(boll: Int): Boolean = snapshot[boll] == 10
        private fun calStrike(boll: Int): Int = 10 + snapshot[boll + 1] + snapshot[boll + 2]

        private fun spare(boll: Int): Boolean = (snapshot[boll] + snapshot[boll + 1]) == 10
        private fun calSpare(boll: Int): Int = 10 + snapshot[boll + 2]
        private fun cal(boll: Int): Int = snapshot[boll] + snapshot[boll + 1]
    }
}
