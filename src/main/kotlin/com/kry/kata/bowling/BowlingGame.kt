package com.kry.kata.bowling

private typealias Frame = Int
private typealias Pins = Array<Int>
private typealias Boll = Int
private typealias Score = Int

class BowlingGame {
    private var pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int {
        val pins: Pins = this.pins

        tailrec fun score(frame: Frame, boll: Boll, score: Score): Int {
            if (frame.end())
                return score

            return score(frame.next(), boll.next(pins), score.score(pins, boll))
        }

        return score(1, 0, 0)
    }

    private fun Frame.end() = this > 10
    private fun Frame.next(): Int = this + 1

    private fun Boll.next(pins: Pins): Int = when {
        pins.strike(this) -> this + 1
        else -> this + 2
    }

    private fun Score.score(pins: Pins, boll: Boll) = this + when {
        pins.strike(boll) -> pins.scoreForStrike(boll)
        pins.spare(boll) -> pins.scoreForSpare(boll)
        else -> pins.score(boll)
    }

    private fun Pins.strike(boll: Int): Boolean = (this[boll]) == 10
    private fun Pins.scoreForStrike(boll: Int): Int = 10 + this[boll + 1] + this[boll + 2]

    private fun Pins.spare(boll: Int): Boolean = (this[boll] + this[boll + 1]) == 10
    private fun Pins.scoreForSpare(boll: Int): Int = 10 + this[boll + 2]

    private fun Pins.score(boll: Int): Int = this[boll] + this[boll + 1]
}
