package com.kry.kata.bowling

class BowlingGame {
    private var pins: MutableList<Int> = mutableListOf()

    fun roll(pins: Int) {
        this.pins.add(pins)
    }

    fun score(): Int {
        var boll = 0;
        var score = 0;

        (1..10).forEach { _ ->
            when {
                strike(boll) -> {
                    score += strikeScore(boll)
                    boll += 1
                }
                spare(boll) -> {
                    score += 10 + pins[boll + 2]
                    boll += 2
                }
                else -> {
                    score += pins[boll] + pins[boll + 1]
                    boll += 2
                }
            }
        }

        return score
    }

    private fun spare(boll: Int) = pins[boll] + pins[boll + 1] == 10

    private fun strike(boll: Int) = pins[boll] == 10

    private fun strikeScore(boll: Int) = 10 + pins[boll + 1] + pins[boll + 2]
}
