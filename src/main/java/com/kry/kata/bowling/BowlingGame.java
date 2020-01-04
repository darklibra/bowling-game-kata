package com.kry.kata.bowling;

public class BowlingGame {
    private static final int MAX_SCORE_IN_FRAME = 10;

    private int[] pins = new int[23];
    private int num;

    public void roll(final int pins) {
        this.pins[num++] = pins;
    }

    public int score() {
        var sum = 0;
        var boll = 0;

        for (int i = 0; i < 10; i++) {
            if (isStrike(boll)) {
                sum += MAX_SCORE_IN_FRAME + nextTwoBollsForStrike(boll);

                boll++;
            } else {
                sum += (isSpare(boll)) ? MAX_SCORE_IN_FRAME + pins[boll + 2]:(pins[boll] + pins[boll + 1]);

                boll += 2;
            }
        }

        return sum;
    }

    private int nextTwoBollsForStrike(final int boll) {
        return pins[boll + 1] + pins[boll + 2];
    }

    private boolean isStrike(final int boll) {
        return pins[boll]==10;
    }

    private boolean isSpare(final int boll) {
        return (pins[boll] + pins[boll + 1])==10;
    }
}
