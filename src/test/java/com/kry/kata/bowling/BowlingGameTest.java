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
    @DisplayName("전체 프레임이 0인 경우")
    public void rollAllZeroTest() {
        rollMany(20, 0);

        Assertions.assertEquals(0, g.score());
    }

    @Test
    @DisplayName("전체 프레임이 1인 경우")
    public void rollAllOneTest() {
        rollMany(20, 1);

        Assertions.assertEquals(20, g.score());
    }

    @Test
    @DisplayName("스페어 테스트")
    public void spareTest() {
        g.roll(4);
        g.roll(6);
        g.roll(3);

        rollMany(17, 0);

        Assertions.assertEquals(16, g.score());
    }

    @Test
    @DisplayName("스트라이크 테스트")
    public void strikeTest() {
        g.roll(10);
        g.roll(3);
        g.roll(3);

        rollMany(16, 0);

        Assertions.assertEquals(22, g.score());
    }

    @Test
    @DisplayName("더블 스트라이크 테스트")
    public void doubleStrikeTest() {
        g.roll(10);
        g.roll(10);
        g.roll(3);

        rollMany(16, 0);

        Assertions.assertEquals(39, g.score());
    }

    @Test
    @DisplayName("PerfectGame")
    public void perfectGame() {
        rollMany(12, 10);

        Assertions.assertEquals(300, g.score());
    }

    private void rollMany(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }
}
