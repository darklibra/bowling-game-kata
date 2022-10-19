package com.kry.kata.bowling;

import kotlin.ranges.IntRange;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

public class BowlingGameTest {

    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @Nested
    class OneTime {
        @Test
        @DisplayName("1회 투구: 1점")
        public void onl() {
            game.roll(1);

            Assertions.assertEquals(1, game.score());
        }
    }

    @Nested
    class Times {
        @Test
        @DisplayName("다회 투구: 0점")
        public void zero() {
            times(0, 20);

            Assertions.assertEquals(0, game.score());
        }

        @Test
        @DisplayName("다회 투구: 1점")
        public void one() {
            times(1, 20);

            Assertions.assertEquals(20, game.score());
        }

        @Test
        @DisplayName("다회 투구: spare")
        public void spare() {
            game.roll(3);
            game.roll(7);
            times(1, 18);

            Assertions.assertEquals(29, game.score());
        }

        @Test
        @DisplayName("다회 투구: strike")
        public void strike() {
            game.roll(10);
            times(1, 18);

            Assertions.assertEquals(30, game.score());
        }

        @Test
        @DisplayName("다회 투구: 퍼펙트")
        public void perfect() {
            times(10, 11);

            Assertions.assertEquals(300, game.score());
        }

        private void times(int pins, int times) {
            IntStream.range(0, times)
                .forEach(idx -> game.roll(pins));
        }
    }
}
