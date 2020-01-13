package com.kry.kata.bowling

class BowlingGame {
    private var pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = Cal.score(this.pins)

    private companion object Cal {
        tailrec fun score(pins: Pins, frame: Frame = 1, boll: Boll = 0, score: Score = 0): Int {
            if (frame > 10)
                return score

            return score(pins, frame.next(), boll.next(pins), score.acc(pins, boll))
        }

        private fun Frame.next(): Int = this + 1

        private fun Boll.next(pins: Pins): Int = this + (if (pins.strike(this)) 1 else 2)

        private fun Pins.strike(boll: Boll): Boolean = this[boll] == 10
        private fun Pins.spare(boll: Boll): Boolean = this[boll] + this[boll + 1] == 10
        private fun Pins.nextTwoBollsForStrike(boll: Boll): Int = this[boll + 1] + this[boll + 2]
        private fun Pins.nextOneBollForSpare(boll: Boll): Int = this[boll + 2]
        private fun Pins.frameScore(boll: Boll): Int = this[boll] + this[boll + 1]

        private fun Score.acc(pins: Pins, boll: Boll) = this + when {
            pins.strike(boll) -> 10 + pins.nextTwoBollsForStrike(boll)
            pins.spare(boll) -> 10 + pins.nextOneBollForSpare(boll)
            else -> pins.frameScore(boll)
        }
    }
}

private typealias Frame = Int
private typealias Pins = Array<Int>
private typealias Boll = Int
private typealias Score = Int
