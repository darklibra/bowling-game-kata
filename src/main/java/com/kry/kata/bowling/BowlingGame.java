package com.kry.kata.bowling;

public class BowlingGame {
    private int[] pins = new int[21];

    private ScoreWriter writer = new ScoreWriter();
    private ScoreCalculator calculator = new ScoreCalculator();

    public void roll(final int pins) {
        writer.write(pins);
    }

    public int score() {
        return calculator.cal();
    }

    private class ScoreWriter {
        private int num = 0;

        public void write(final int pins) {
            BowlingGame.this.pins[num++] = pins;
        }
    }

    private class ScoreCalculator {
        public int cal() {
            int score = 0;
            int boll = 0;

            for (int i = 0; i < 10; i++) {
                if (strike(boll)) {
                    score += 10 + nextTwoBollsForStrike(boll);
                    boll += 1;
                    continue;
                }

                final int fs = frameScoreAt(boll);

                score += fs;
                if (fs==10) {
                    score += nextOneBollForSpare(boll);
                }

                boll += 2;
            }

            return score;
        }

        private int nextOneBollForSpare(final int boll) {
            return pins[boll + 2];
        }

        private int nextTwoBollsForStrike(final int boll) {
            return pins[boll + 1] + pins[boll + 2];
        }

        private int frameScoreAt(final int boll) {
            return pins[boll] + pins[boll + 1];
        }

        private boolean strike(final int boll) {
            return pins[boll]==10;
        }
    }
}
