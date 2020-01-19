package com.kry.kata.bowling;

public class BowlingGame {
    private int[] pins = new int[21];
    private int num = 0;

    public void roll(final int pins) {
        this.pins[num++] = pins;
    }

    public int score() {
        int score = 0;
        int boll = 0;

        final FrameCond cond = new FrameCond();

        for (int i = 0; i < 10; i++) {
            final boolean strike = cond.isStrike(boll);

            if (strike) {
                score += 10 + nextTwoBollForStrike(boll);
            } else if (cond.isSpare(boll)) {
                score += 10 + nextOneBollForSpare(boll);
            } else {
                score += scoreForFrame(boll);
            }

            boll += (strike) ? 1:2;
        }

        return score;
    }

    private int nextTwoBollForStrike(final int boll) {
        return pins[boll + 1] + pins[boll + 2];
    }

    private int nextOneBollForSpare(final int boll) {
        return pins[boll + 2];
    }

    private int scoreForFrame(final int boll) {
        return pins[boll] + pins[boll + 1];
    }

    class FrameCond {
        boolean isStrike(final int boll) {
            return pins[boll]==10;
        }

        boolean isSpare(final int boll) {
            return pins[boll] + pins[boll + 1]==10;
        }
    }
}
