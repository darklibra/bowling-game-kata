package com.kry.kata.bowling;

public class BowlingGame {
    private final int MAX_FRAME_COUNT = 10;

    private int pins[] = new int[21];

    private ScoreWriter writer;
    private ScoreCalculator calculator;
    private FrameStatusChecker checker;

    public BowlingGame() {
        this.writer = new ScoreWriter();
        this.calculator = new ScoreCalculator();
        this.checker = new FrameStatusChecker();
    }

    public void roll(final int pins) {
        this.writer.roll(pins);
    }

    public int score() {
        var score = 0;
        var boll = 0;

        for (int i = 0; i < MAX_FRAME_COUNT; i++) {
            if (checker.strike(boll)) {
                score += calculator.strike(boll);
                boll++;
            } else if (checker.spare(boll)) {
                score += calculator.spare(boll);
                boll += 2;
            } else {
                score += calculator.frameScore(boll);
                boll += 2;
            }
        }

        return score;
    }

    class ScoreWriter {
        private int num = 0;

        public void roll(final int pins) {
            BowlingGame.this.pins[num++] = pins;
        }
    }

    class ScoreCalculator {
        int strike(final int boll) {
            return 10 + pins[boll + 1] + pins[boll + 2];
        }

        int spare(final int boll) {
            return 10 + pins[boll + 2];
        }

        int frameScore(final int boll) {
            return pins[boll] + pins[boll + 1];
        }
    }

    class FrameStatusChecker {
        private final int MAX_SCORE_IN_FRAME = 10;

        public boolean strike(final int boll) {
            return pins[boll]==MAX_SCORE_IN_FRAME;
        }

        public boolean spare(final int boll) {
            return (pins[boll] + pins[boll + 1])==MAX_SCORE_IN_FRAME;
        }
    }
}
