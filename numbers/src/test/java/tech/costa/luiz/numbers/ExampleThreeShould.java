package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Example three should.
 */
class ExampleThreeShould {

    /**
     * Store a small amount of items.
     */
    @DisplayName("For a small amount use the appropriate number type")
    @Test
    void storeASmallAmountOfItems() {
        Short players = Short.valueOf("11");
        Byte gameBalls = Byte.valueOf("5");
        assertAll(() -> {
            short playersExpected = 11;
            assertEquals(playersExpected, players.intValue());
            short gameBallsExpected = 5;
            assertEquals(gameBallsExpected, gameBalls.intValue());
            assertThrows(NumberFormatException.class, () ->
                        assertEquals(32768, Short.valueOf("32768").intValue()));
        });
    }

}