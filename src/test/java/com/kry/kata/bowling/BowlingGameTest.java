package com.kry.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @Test
    @DisplayName("전체 프레임 0 테스트")
    public void rollAllZero() {
        manyTimes(20, 0);

        Assertions.assertEquals(0, game.score());
    }

    @Test
    @DisplayName("전체 프레임 1 테스트")
    public void rollAllOne() {
        manyTimes(20, 1);

        Assertions.assertEquals(20, game.score());
    }

    @Test
    @DisplayName("spare 테스트")
    public void spare() {
        game.roll(3);
        game.roll(7);
        game.roll(3);

        manyTimes(17, 0);

        Assertions.assertEquals(16, game.score());
    }

    @Test
    @DisplayName("strike 테스트")
    public void strike() {
        game.roll(10);
        game.roll(3);
        game.roll(3);

        manyTimes(16, 0);

        Assertions.assertEquals(22, game.score());
    }

    @Test
    @DisplayName("nine consecutive strike 테스트")
    public void nineConsecutiveStrike() {
        manyTimes(9, 10);
        manyTimes(2, 0);

        Assertions.assertEquals(240, game.score());
    }

    @Test
    @DisplayName("perfect game 테스트")
    public void perfectGame() {
        manyTimes(12, 10);

        Assertions.assertEquals(300, game.score());
    }

    private void manyTimes(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
