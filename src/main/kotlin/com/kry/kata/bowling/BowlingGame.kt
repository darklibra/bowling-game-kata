package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = Score(this.pins).score(10)

    class Score(pins: Array<Int>) {
        private val snap = pins.toList()

        fun score(frame: Int): Int {
            return (1..frame).fold(ScoreAcc.init()) { acc, _ -> cal(acc) }.score
        }

        private fun cal(acc: ScoreAcc): ScoreAcc {
            val cur = acc.boll

            if (strike(cur)) {
                return acc.nextOne(10 + snap[cur + 1] + snap[cur + 2])
            }

            return when (val fs = storeAt(cur)) {
                10 -> acc.nextTwo(10 + snap[cur + 2])
                else -> acc.nextTwo(fs)
            }
        }

        private fun storeAt(cur: Int) = snap[cur] + snap[cur + 1]

        private fun strike(boll: Int) = snap[boll] == 10

    }
}

class ScoreAcc(val boll: Int, val score: Int) {
    fun nextTwo(s: Int): ScoreAcc = ScoreAcc(boll + 2, score + s)

    fun nextOne(s: Int): ScoreAcc = ScoreAcc(boll + 1, score + s)

    companion object Factory {
        fun init(): ScoreAcc = ScoreAcc(0, 0)
    }
}

