package com.kry.kata.bowling

class BowlingGame {
    private val store = Store()

    fun roll(pins: Int) = store.store(pins)

    fun score(): Int = Calculator(store.getScore()).score()

    class Store {
        private val pins = Array(21) { 0 }

        private var num = 0

        fun store(pins: Int) {
            this.pins[num++] = pins
        }

        fun getScore(): List<Int> = pins.toList()
    }

    class Calculator(pins: List<Int>) {
        private val snap = pins
        private var score = 0
        private var calculated = false

        fun score(): Int {
            if (calculated) {
                return score
            }

            this.score = cal()
            this.calculated = true

            return this.score
        }

        private fun cal(): Int {
            var score = 0
            var boll = 0

            repeat(10) {
                if (strike(boll)) {
                    score += scoreStrikeAt(boll)
                    boll += 1
                } else if (spare(boll)) {
                    score += storeSpareAt(boll)
                    boll += 2
                } else {
                    score += storeAt(boll)
                    boll += 2
                }
            }

            return score
        }

        private fun storeSpareAt(boll: Int) = 10 + snap[boll + 2]

        private fun scoreStrikeAt(boll: Int) = 10 + snap[boll + 1] + snap[boll + 2]

        private fun spare(boll: Int) = snap[boll] + snap[boll + 1] == 10

        private fun storeAt(boll: Int) = snap[boll] + snap[boll + 1]

        private fun strike(boll: Int) = snap[boll] == 10
    }
}
