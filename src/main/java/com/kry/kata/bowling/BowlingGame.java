package com.kry.kata.bowling;

public class BowlingGame {
    private final int MAX_FRAME = 10;
    private final int MAX_SCORE_FOR_FRAME = 10;

    private int rollNumber = 0;
    private int[] rolls = new int[21];

    public void roll(final int pins) {
        this.rolls[rollNumber++] = pins;
    }

    public int score() {
        var sum = 0;
        var frame = 0;

        for (int i = 0; i < MAX_FRAME; i++) {
            if (isStrike(frame)) {
                sum += MAX_SCORE_FOR_FRAME + strikeBonus(frame);
                frame += 1;
            } else if (isSpare(frame)) {
                sum += MAX_SCORE_FOR_FRAME + spareBonus(frame);
                frame += 2;
            } else {
                sum += frameScore(frame);
                frame += 2;
            }
        }

        return sum;
    }

    private int frameScore(final int roll) {
        return rolls[roll] + rolls[roll + 1];
    }

    private int spareBonus(final int roll) {
        return rolls[roll + 2];
    }

    private int strikeBonus(final int roll) {
        return rolls[roll + 1] + rolls[roll + 2];
    }

    private boolean isSpare(final int roll) {
        return frameScore(roll) == MAX_SCORE_FOR_FRAME;
    }

    private boolean isStrike(final int roll) {
        return rolls[roll] == MAX_SCORE_FOR_FRAME;
    }
}
