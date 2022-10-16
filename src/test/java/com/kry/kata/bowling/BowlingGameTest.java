package com.kry.kata.bowling;

import org.junit.jupiter.api.*;

public class BowlingGameTest {

    private BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @Nested
    class OneTime {
        @Test
        @DisplayName("0점 롤링 테스트")
        public void rolledZero() {
            game.roll(0);

            Assertions.assertEquals(0, game.score());
        }

        @Test
        @DisplayName("1점 롤링 테스트")
        public void rolledOne() {
            game.roll(1);

            Assertions.assertEquals(1, game.score());
        }
    }

    @Nested
    class Times {
        @Test
        @DisplayName("1점 롤링 테스트")
        public void rolledOne() {
            times(20, 1);

            Assertions.assertEquals(20, game.score());
        }

        @Test
        @DisplayName("스페어 테스트")
        public void spare() {
            game.roll(5);
            game.roll(5);
            game.roll(3);

            Assertions.assertEquals(16, game.score());
        }

        @Test
        @DisplayName("스트라이크 테스트")
        public void strike() {
            game.roll(10);
            game.roll(5);
            game.roll(4);

            Assertions.assertEquals(28, game.score());
        }

        @Test
        @DisplayName("퍼펙트 테스트")
        public void perfect() {
            times(11, 10);

            Assertions.assertEquals(300, game.score());
        }

        private void times(int times, int score) {
            for (int i = 0; i < times; i++) {
                game.roll(score);
            }
        }
    }

}
