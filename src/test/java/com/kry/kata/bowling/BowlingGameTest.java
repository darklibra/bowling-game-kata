package com.kry.kata.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    private BowlingGame game;

    @BeforeEach
    public void init() {
      this.game = new BowlingGame();
    }

    @Test
    @DisplayName("0점 투구 테스트")
    public void rollWithZeroScore() {
        game.roll(0);

        Assertions.assertEquals(0, game.score());
    }

    @Test
    @DisplayName("1점 투구 테스트")
    public void rollWithOneScore() {
        game.roll(1);

        Assertions.assertEquals(1, game.score());
    }

    @Test
    @DisplayName("spare")
    public void spare() {
        game.roll(3);
        game.roll(7);
        game.roll(5);
        game.roll(3);

        Assertions.assertEquals(23, game.score());
    }

    @Test
    @DisplayName("strike")
    public void strike() {
        game.roll(10);
        game.roll(7);
        game.roll(1);
        game.roll(1);

        Assertions.assertEquals(27, game.score());
    }

    @Test
    @DisplayName("perfect")
    public void perfect() {
        for (int i = 0; i < 11; i++) {
          game.roll(10);
        }

        Assertions.assertEquals(300, game.score());
    }
}

