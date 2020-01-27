package com.kry.kata.bowling

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = BowlingGameScore(this.pins.toList()).cal()

    class BowlingGameScore(private val pins: List<Int>) {
        fun cal(): Int {
            var boll = 0

            return (1..10).map {
                // boll 증가를 먼저 하기 위해 현재 정보를 저장 한다.
                var cur = boll

                if (strike(boll)) {
                    boll += 1
                    scoreForStrike(cur)
                } else if (spare(boll)) {
                    boll += 2
                    scoreForSpare(cur)
                } else {
                    boll += 2
                    scoreForNormal(cur)
                }
            }.sum()
        }

        private fun strike(boll: Int) = pins[boll] == 10
        private fun scoreForStrike(boll: Int) = 10 + pins[boll + 1] + pins[boll + 2]

        private fun spare(boll: Int) = pins[boll] + pins[boll + 1] == 10
        private fun scoreForSpare(boll: Int) = 10 + pins[boll + 2]

        private fun scoreForNormal(boll: Int) = pins[boll] + pins[boll + 1]
    }

}
