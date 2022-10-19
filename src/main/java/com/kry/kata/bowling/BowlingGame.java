package com.kry.kata.bowling;

public class BowlingGame {

    private int[] pins = new int[21];
    private int cur = 0;

    public void roll(final int pins) {
        this.pins[cur] = pins;
        this.cur += 1;

    }

    public int score() {
        int score = 0;

        for (int i = 0; i < cur; ) {
            int moveTo = isStrike(i) ? 1 : 2;

            score += calFrameScore(i);

            i += moveTo;
        }

        return score;
    }

    private int calFrameScore(int at) {
        if (isStrike(at)) {
            return 10 + this.pins[at + 1] + this.pins[at + 2];
        }

        final int frameScore = this.pins[at] + this.pins[at + 1];

        if (frameScore == 10) {
            return 10 + this.pins[at + 2];
        }

        return frameScore;
    }

    private boolean isStrike(int i) {
        return this.pins[i] == 10;
    }
}