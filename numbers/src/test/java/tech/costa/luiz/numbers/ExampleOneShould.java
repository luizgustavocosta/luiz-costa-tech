package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type Example one should.
 */
class ExampleOneShould {

    private final Long primaryKeyFromDataBase = Long.MAX_VALUE;

    /**
     * Thrown an exception to transform long to integer.
     */
    @DisplayName("Exception when try to associate the database pk value")
    @Test
    void thrownAnExceptionToTransformLongToInteger() {
        assertThrows(NumberFormatException.class, () ->
                Integer.parseInt(primaryKeyFromDataBase.toString()));
    }

    /**
     * Transform long to big integer.
     */
    @DisplayName("Accepted the database pk value")
    @Test
    void transformLongToBigInteger() {
        BigInteger myChosenType = new BigInteger(String.valueOf(primaryKeyFromDataBase));
        assertEquals(myChosenType.toString(), primaryKeyFromDataBase.toString());
    }
}