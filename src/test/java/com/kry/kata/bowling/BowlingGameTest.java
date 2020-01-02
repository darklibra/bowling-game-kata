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
    @DisplayName("전체 프레임에서 쓰러트린 핀수 0인 경우")
    public void allZero() {
        many(20, 0);

        Assertions.assertEquals(0, game.score());
    }

    @Test
    @DisplayName("전체 프레임에서 쓰러트린 핀수 1인 경우")
    public void allOne() {
        many(20, 1);

        Assertions.assertEquals(20, game.score());
    }

    @Test
    @DisplayName("스페어 처리 테스트")
    public void spare() {
        game.roll(5);
        game.roll(5);
        game.roll(3);

        many(17, 0);

        Assertions.assertEquals(16, game.score());
    }

    @Test
    @DisplayName("스트라이크 처리 테스트")
    public void strike() {
        game.roll(10); // strike
        game.roll(3);
        game.roll(5);
        many(26, 0);

        Assertions.assertEquals(26, game.score());
    }

    @Test
    @DisplayName("11회 연속 스트라이크")
    public void elevenBagger() throws Exception {
        many(11, 10);
        game.roll(3);

        Assertions.assertEquals(293, game.score());
    }

    @Test
    @DisplayName("퍼펙트 게임 테스트")
    public void perfectGame() throws Exception {
        many(12, 10);

        Assertions.assertEquals(300, game.score());
    }

    private void many(final int times, final int pins) {
        for (int i =     0; i < times; i++) {
            game.roll(pins);
        }
    }
}
