package com.kry.kata.bowling;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    private BowlingGame game;

    @BeforeEach
    public void init() {
      game = new BowlingGame();
    }

    @Test
    @DisplayName("0점 롤링 테스트")
    public void rollZero() {
      game.roll(0);

      Assertions.assertEquals(0, game.score());
    }

    @Test
    @DisplayName("1점 롤링 테스트")
    public void rolledOne() {
      game.roll(1);

      Assertions.assertEquals(1, game.score());
    }

    @Test
    @DisplayName("spare")
    public void spare() {
      game.roll(3);
      game.roll(7);
      game.roll(3);
      game.roll(3);

      Assertions.assertEquals(19, game.score());
    }

    @Test
    @DisplayName("strike")
    public void strike() {
      game.roll(10);
      game.roll(5);
      game.roll(3);
      game.roll(3);

      Assertions.assertEquals(29, game.score());
    }

    @Test
    @DisplayName("퍼펙트 테스트")
    public void prefect() {
      times(11, 10);

      Assertions.assertEquals(300, game.score());
    }

    private void times(int times, int pins) {
      IntStream.range(0, times)
      .forEach(idx -> {
        this.game.roll(pins);
      });;
    }
}
