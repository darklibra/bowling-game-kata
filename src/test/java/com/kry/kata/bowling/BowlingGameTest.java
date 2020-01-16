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
    @DisplayName("10 프레임 점수가 0인 테스트")
    public void all_frames_score_is_zero() {
        rollMany(20, 0);

        Assertions.assertEquals(0, g.score());
    }

    @Test
    @DisplayName("10 프레임 점수가 1인 테스트")
    public void all_frames_score_is_one() {
        rollMany(20, 1);

        Assertions.assertEquals(20, g.score());
    }

    @Test
    @DisplayName("1 스페어")
    public void one_spare() {
        g.roll(7);
        g.roll(3);
        g.roll(3);

        rollMany(17, 0);

        Assertions.assertEquals((7 + 3 + 3) + 3, g.score());
    }

    @Test
    @DisplayName("1 스트라이크")
    public void one_strike() {
        g.roll(10);
        g.roll(3);
        g.roll(3);

        rollMany(16, 0);

        Assertions.assertEquals((10 + 3 + 3) + 3 + 3, g.score());
    }

    @Test
    @DisplayName("퍼펙트 게임")
    public void perfect_game() {
        rollMany(12, 10);

        Assertions.assertEquals(300, g.score());
    }

    private void rollMany(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }
}
