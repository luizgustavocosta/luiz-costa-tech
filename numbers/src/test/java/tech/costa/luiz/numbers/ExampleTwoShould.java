package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Example two should.
 */
class ExampleTwoShould {

    /**
     * Sum numbers using float.
     */
    @DisplayName("Repetitively sum using float")
    @Test
    void sumNumbersUsingFloat() {
        float decimalIncremental = 0.2f;
        float salary = 1.0f;
        float maxSalary = 2.0f;
        while (salary < maxSalary) {
            salary = salary + decimalIncremental;
        }
        assertNotEquals(maxSalary, salary);
    }

    /**
     * Sum numbers using big decimals.
     */
    @DisplayName("Repetitively sum using BigDecimal")
    @Test
    void sumNumbersUsingBigDecimals() {
        BigDecimal decimalIncremental = BigDecimal.valueOf(0.2);
        BigDecimal salary = BigDecimal.valueOf(1.0);
        BigDecimal maxSalary = BigDecimal.valueOf(2.0);
        while (salary.compareTo(maxSalary) < 0) {
            salary = salary.add(decimalIncremental);
        }
        assertEquals(maxSalary, salary);
    }

}