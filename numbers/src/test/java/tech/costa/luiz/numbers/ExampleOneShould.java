package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExampleOneShould {

    private Long primaryKeyFromDataBase = Long.MAX_VALUE;

    @DisplayName("Exception when try to associate the database pk value")
    @Test
    void thrownAnExceptionToTransformLongToInteger() {
        assertThrows(NumberFormatException.class, () ->
                Integer.parseInt(primaryKeyFromDataBase.toString()));
    }

    @DisplayName("Accepted the database pk value")
    @Test
    void transformLongToBigInteger() {
        BigInteger myChosenType = new BigInteger(String.valueOf(primaryKeyFromDataBase));
        assertEquals(myChosenType.toString(), primaryKeyFromDataBase.toString());
    }



}