package com.kry.structure

class BowlingGame {
    private val store = Store()

    fun roll(pins: Int) {
        store.save(pins)
    }

    fun score(): Int = Calculator(store).score

    class Store {
        private val pins = Array(21) { 0 }
        private var num = 0;

        fun save(pins: Int) {
            this.pins[num++] = pins
        }

        fun all() = this.pins.toList()
    }

    class Calculator(store: Store) {
        val score by lazy { cal(store.all()) }

        companion object Obj {
            private fun cal(pins: List<Int>): Int {
                var boll = 0
                var score = 0

                var strike = fun() = pins[boll]     == 10
                var nextTwoBoll = fun(): Int { return pins[boll + 1] + pins[boll + 2] }

                var spare = fun() = pins[boll] + pins[boll + 1] == 10
                var nextOneBoll = fun(): Int { return pins[boll + 2] }

                var scoreInFrame = fun() = pins[boll] + pins[boll + 1]

                var moveOne = fun() { boll += 1 }
                var moveTwo = fun() { boll += 2 }

                repeat(10) r@{
                    if (strike()) {
                        score += 10 + nextTwoBoll()
                        moveOne()
                        return@r
                    }

                    score += if (spare()) 10 + nextOneBoll() else scoreInFrame()
                    moveTwo()
                }

                return score
            }
        }
    }
}
