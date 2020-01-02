package com.kry.kata.bowling;

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

    private static class Frames {
        public Frame first;

        public Frame current;

        public Frames() {
            this.first = new HeadFrame();
            this.current = this.first;
        }

        public void roll(final int pins) {
            this.current = this.current.roll(pins);
        }

        public int score() {
            return this.first.score();
        }
    }

    private static class Frame {
        private static final Frame TERMINATE = new Frame(0) {
            @Override
            public Frame roll(final int pins) {
                return this;
            }

            @Override
            public int score() {
                return 0;
            }
        };

        protected int num;
        protected int rest;
        protected int first = 0;
        protected int second = 0;

        protected Frame next = TERMINATE;

        public Frame(final int rest) {
            this.num = 0;
            this.rest = rest;
        }

        public Frame roll(final int pins) {
            if (num == 0) {
                this.first = pins;
                this.num++;

                return this;
            } else if (!isStrike() && num == 1) {
                this.second = pins;
                this.num++;

                return this;
            } else {
                return nextFrame(pins);
            }
        }

        private Frame nextFrame(final int pins) {
            this.next = (this.rest == 1) ? new LastFrame() : new Frame(this.rest - 1);

            return this.next.roll(pins);
        }

        public int score() {
            if (isStrike()) {
                return 10 + this.strikeScore() + this.next.score();
            }

            final int frameScore = this.first + this.second;
            if (frameScore == 10) {
                return 10 + this.next.first + this.next.score();
            } else {
                return frameScore + this.next.score();
            }
        }

        private int strikeScore() {
            final Frame next = this.next;
            if (next.isStrike()) {
                return next.first + next.next.first;
            }
            return next.first + next.second;
        }

        protected boolean isStrike() {
            return this.first == 10;
        }
    }

    private static class HeadFrame extends Frame {
        public HeadFrame() {
            super(10);
        }

        @Override
        public Frame roll(final int pins) {
            this.next = new Frame(this.rest - 1);

            return this.next.roll(pins);
        }

        @Override
        public int score() {
            return this.next.score();
        }
    }

    private static class LastFrame extends Frame {
        private int third = 0;

        public LastFrame() {
            super(0);
        }

        @Override
        public Frame roll(final int pins) {
            if (num == 0) {
                this.first = pins;
                this.num++;
            } else if (num == 1) {
                this.second = pins;
                this.num++;
            } else if (num == 2) {
                this.third = pins;
                this.num++;
            }

            return this;
        }

        @Override
        public int score() {
            return ((isStrike()) ? 20 : 0) + this.third + super.score();
        }
    }
}
