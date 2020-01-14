package com.kry.kata.bowling

class BowlingGame {
    private var pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        fun spare(boll: Int): Boolean = pins[boll] + pins[boll + 1] == 10
        fun strike(boll: Int): Boolean = pins[boll] == 10

        fun scoreFrame(boll: Int): Int = this.pins[boll] + this.pins[boll + 1]
        fun scoreSpare(boll: Int): Int = 10 + pins[boll + 2]
        fun scoreStrike(boll: Int): Int = 10 + pins[boll + 1] + pins[boll + 2]

        return (1..10).fold(Pair(0, 0)) { (boll, score), _ ->
            val newScore = score + when {
                strike(boll) -> scoreStrike(boll)
                spare(boll) -> scoreSpare(boll)
                else -> scoreFrame(boll)
            }

            var newBoll = boll + if (strike(boll)) 1 else 2

            Pair(newBoll, newScore)
        }.second
    }
}
