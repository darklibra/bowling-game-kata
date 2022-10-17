package com.kry.kata.bowling;

public class BowlingGame {
  private static final int MAX_PINS = 10;

  private int score[] = new int[21];
  private int cur = 0;

  public void roll(final int pins) {
    this.score[this.cur] = pins;
    this.cur += 1;
  }

  public Integer score() {
    int score = 0;

    for (int i = 0; i < this.cur;) {
      score += calFrameScoreAt(i);

      i += (isStrike(i) ? 1 : 2);
    }

    return score;
  }

  private int calFrameScoreAt(int pos) {
    if (isStrike(pos)) {
      return MAX_PINS + this.score[pos + 1] + this.score[pos + 2];
    } else if (isSpare(pos)) {
      return MAX_PINS + this.score[pos + 2];
    }

    return getFrameScore(pos);
  }

  private boolean isSpare(int pos) {
    return getFrameScore(pos) == MAX_PINS;
  }

  private int getFrameScore(int pos) {
    return this.score[pos] + this.score[pos + 1];
  }

  private boolean isStrike(int pos) {
    return this.score[pos] == MAX_PINS;
  }
}
