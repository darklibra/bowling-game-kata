package com.kry.kata.bowling;

public class BowlingGame {
    private int[] score = new int[21];
    private int pos = 0;

    public void roll(final int pins) {
        this.score[pos] = pins;
        pos++;
    }

    public int score() {
        int total = 0;

        for (int pt = 0; pt < pos; ) {
            total += calFrameScore(pt);

            pt += (isStrike(pt) ? 1 : 2);
        }

        return total;
    }

    private int calFrameScore(int position) {
        if (isStrike(position)) {
            return getStrikeScoreAt(position);
        } else if (isSpare(position)) {
            return getSpareScoreAt(position);
        }

        return getFrameScoreAt(position);
    }

    private int getSpareScoreAt(int position) {
        return 10 + this.score[position + 2];
    }

    private int getStrikeScoreAt(int position) {
        return 10 + score[position + 1] + score[position + 2];
    }

    private boolean isStrike(int position) {
        return score[position] == 10;
    }

    private boolean isSpare(int position) {
        return getFrameScoreAt(position) == 10;
    }

    private int getFrameScoreAt(int position) {
        return score[position] + score[position + 1];
    }
}
