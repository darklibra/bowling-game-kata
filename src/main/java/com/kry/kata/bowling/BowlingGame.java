package com.kry.kata.bowling;

public class BowlingGame {
    private int[] score = new int[21];
    private int cur = 0;

    public void roll(final int pins) {
      this.score[cur] = pins;
      this.cur += 1;
    }

    public Integer score() {
        int score = 0;
        for (int i = 0; i < this.cur; ) {
          int moveTo = 0;
          if (isStrikeAt(i)) {
            score += getStrikeScore(i);

            moveTo = 1;
          } else {
            score += getFrameAndSpareScore(i);

            moveTo = 2;
          }

          i += moveTo;
        }

        return score;
    }

    private int getStrikeScore(int i) {
      return 10 + this.score[i + 1] + this.score[i + 2];
    }

    private int getFrameAndSpareScore(int i) {
      int frame = frameScoreAt(i);

      if (frame == 10) {
        return 10 + this.score[i + 2];
      }

      return frame;
    }

    private int frameScoreAt(int at) {
      return this.score[at] + this.score[at + 1];
    }

    private boolean isStrikeAt(int at) {
      return this.score[at] == 10;
    }
}

