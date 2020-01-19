package com.kry.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    private BowlingGame g;

    @BeforeEach
    void setUp() {
        g = new BowlingGame();
    }

    @Test
    @DisplayName("전체 프레임 점수 0")
    public void allFrameScoreZro() {
        manyTimes(20, 0);

        Assertions.assertEquals(0, g.score());
    }

    @Test
    @DisplayName("전체 프레임 점수 1")
    public void allFrameScoreOne() {
        manyTimes(20, 1);

        Assertions.assertEquals(20, g.score());
    }

    @Test
    @DisplayName("스페어 테스트")
    public void 스페어_테스트() {
        g.roll(7);
        g.roll(3);
        g.roll(3);

        manyTimes(17, 0);

        Assertions.assertEquals((7 + 3 + 3) + 3, g.score());
    }

    @Test
    @DisplayName("스트라이크 테스트")
    public void 스트라이크_테스트() {
        g.roll(7);
        g.roll(3);
        g.roll(3);

        manyTimes(17, 0);

        Assertions.assertEquals((7 + 3 + 3) + 3, g.score());
    }

    @Test
    @DisplayName("퍼펙트 게임 테스트")
    public void 퍼펙트_게임_테스트() {
        g.roll(7);
        g.roll(3);
        g.roll(3);

        manyTimes(17, 0);

        Assertions.assertEquals((7 + 3 + 3) + 3, g.score());
    }

    private void manyTimes(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }
}
