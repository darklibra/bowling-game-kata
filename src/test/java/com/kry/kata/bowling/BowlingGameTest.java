package com.kry.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BowlingGameTest {

    private BowlingGame g;

    @BeforeEach
    void setUp() {
        g = new BowlingGame();
    }

    @ParameterizedTest(name = "{index} => all pins : {0}")
    @DisplayName("동일한 점수로 20번 점수 기록 테스트")
    @CsvSource({"0, 0", "1, 20", "2, 40", "3, 60", "4, 80"})
    public void times20AndSamePins(final int pins, final int expected) {
        manyRoll(20, pins);

        Assertions.assertEquals(expected, g.score());
    }

    @Test
    @DisplayName("스페어")
    public void spare() {
        g.roll(7);
        g.roll(3);
        g.roll(3);

        manyRoll(17, 0);

        Assertions.assertEquals(16, g.score());
    }

    @Test
    @DisplayName("스트라이크")
    public void strike() {
        g.roll(10);
        g.roll(3);
        g.roll(3);

        manyRoll(16, 0);

        Assertions.assertEquals(22, g.score());
    }

    @Test
    @DisplayName("퍼펙트 게임")
    public void perfectGame() {
        manyRoll(12, 10);

        Assertions.assertEquals(300, g.score());
    }

    private void manyRoll(final int times, final int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }
}
