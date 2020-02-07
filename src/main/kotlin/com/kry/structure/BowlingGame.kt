package com.kry.structure

class BowlingGame {
    private val pins = Array(21) { 0 }
    private var num = 0

    fun roll(pins: Int) {
        this.pins[num++] = pins
    }

    fun score(): Int = Score { this.pins.toList() }.score(10)

    class Score(factory: PinsFactory) {
        private val pins = factory.invoke()

        fun score(frame: Int): Int {
            var score = 0
            var boll = 0

            repeat(frame) {
                val status = FramePinStatus.valueOf(pins, boll)

                score += status.score(pins, boll)
                boll += status.move()
            }
            return score
        }
    }

    enum class FramePinStatus {
        STRIKE {
            override fun score(pins: List<Int>, boll: Int): Int = 10 + pins[boll + 1] + pins[boll + 2]

            override fun move(): Int = 1
        },
        SPARE {
            override fun score(pins: List<Int>, boll: Int): Int = 10 + pins[boll + 2]

            override fun move(): Int = 2
        },
        NORMAL {
            override fun score(pins: List<Int>, boll: Int): Int = pins[boll] + pins[boll + 1]

            override fun move(): Int = 2
        };

        abstract fun score(pins: List<Int>, boll: Int): Int
        abstract fun move(): Int

        companion object {
            fun valueOf(pins: List<Int>, boll: Int): FramePinStatus {
                return when {
                    pins[boll] == 10 -> STRIKE
                    pins[boll] + pins[boll + 1] == 10 -> SPARE
                    else -> NORMAL
                }
            }
        }
    }
}

private typealias PinsFactory = () -> List<Int>
