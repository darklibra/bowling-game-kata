package com.kry.kata.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    @Test
    @DisplayName("볼링 게임 생성 테스트")
    public void created() {
        new BowlingGame();
    }
}
