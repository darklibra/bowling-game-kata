package com.kry.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("볼링 게임 테스트")
public class BowlingGameTest {

    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @DisplayName("단일 값으로 점수 계산")
    @ParameterizedTest(name = "{index} ==> times={0}, pins={1}, expected={2}")
    @CsvSource({"20, 0, 0", "20, 1, 20", "20, 4, 80"})
    public void rollManySinglePins(final int times, final int pins, final int expected) {
        rollMany(times, pins);

        Assertions.assertEquals(expected, game.score());
    }

    @Test
    public void perfectGame() {
        rollMany(12, 10);

        Assertions.assertEquals(300, game.score());
    }

    @Test
    @DisplayName("스페어 테스트")
    public void spare() {
        game.roll(5);
        game.roll(5);
        game.roll(3);

        rollMany(17, 0);

        Assertions.assertEquals(16, game.score());
    }

    @Test
    @DisplayName("스트라이크 테스트")
    public void strike() {
        game.roll(10);
        game.roll(3);
        game.roll(5);

        rollMany(16, 0);

        Assertions.assertEquals(26, game.score());
    }

    private void rollMany(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
