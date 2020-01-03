package com.kry.kata.bowling;

import org.jetbrains.annotations.NotNull;

public class BowlingGame {
    private Frames frames;

    public BowlingGame() {
        this.frames = new Frames();
    }

    public void roll(final int pins) {
        this.frames.roll(pins);
    }

    public int score() {
        return this.frames.score();
    }

    private static final Frame EMPTY = new Frame(null) {
        @Override
        public void roll(final int pins) {

        }

        @Override
        public int score() {
            return 0;
        }

        @Override
        public boolean isNext() {
            return true;
        }
    };

    private static class Frames {
        static final int MAX_FRAME = 10;

        private int num;

        private Frame last = EMPTY;

        public int score() {
            return this.last.score();
        }

        public void roll(final int pins) {
            if (this.last.isNext()) {
                this.last = nextFrame();
            }

            this.last.roll(pins);
        }

        @NotNull
        private Frame nextFrame() {
            this.num++;

            if (this.num==MAX_FRAME) {
                return new LastFrame(this.last);
            }

            return new Frame(this.last);
        }
    }

    private static class Frame {
        protected int boll;

        protected int first;

        protected int second;

        protected Frame prev;

        public Frame(final Frame prev) {
            this.prev = prev;
        }

        public int score() {
            final int frameScore = this.first + this.second;

            final int baseScore = prev.score() + frameScore;

            if (prev.strike()) {
                return baseScore + nextTwoBallsForStrike();
            } else if (prev.spare()) {
                return baseScore + this.first;
            } else {
                return baseScore;
            }
        }

        protected int nextTwoBallsForStrike() {
            // 이전 프레임이 스트라이크 이고, 2 프레임 전이 스트라이크 이면 현재 스코어의 첫번째 점수를 추가 한다.
            // 이는 2연속 스트카리크 일경우 첫번째 스크라이크 점수 계산에 현재 프레임의 첫번째 점수가 사용되기 때문이다.
            return this.first + (prev.prev.strike() ? this.first:this.second);
        }

        private boolean spare() {
            return (this.first + this.second)==10;
        }

        private boolean strike() {
            return (this.first)==10;
        }

        public void roll(final int pins) {
            if (this.boll==0) {
                this.first = pins;
            } else if (this.boll==1) {
                this.second = pins;
            }

            this.boll++;
        }

        public boolean isNext() {
            return (strike()) || this.boll==2;
        }
    }

    private static class LastFrame extends Frame {
        private int third;

        public LastFrame(final Frame prev) {
            super(prev);
        }

        @Override
        public void roll(final int pins) {
            if (this.boll==0) {
                this.first = pins;
            } else if (this.boll==1) {
                this.second = pins;
            } else if (this.boll==2) {
                this.third = pins;
            }

            this.boll++;
        }

        @Override
        public int score() {
            return super.score() + this.third;
        }

        @Override
        protected int nextTwoBallsForStrike() {
            return this.first + this.second + (prev.prev.strike() ? this.first:0);
        }

        @Override
        public boolean isNext() {
            return false;
        }
    }
}
