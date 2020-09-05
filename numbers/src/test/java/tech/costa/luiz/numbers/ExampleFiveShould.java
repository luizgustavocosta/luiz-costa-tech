package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Example five should.
 */
class ExampleFiveShould {

    /**
     * Use accumulator given a list.
     */
    @DisplayName("How to use the accumulators max and sum")
    @Test
    void useAccumulatorGivenAList() {

        final long identity = 0L;
        final LongAccumulator accumulatorMax = new LongAccumulator(Long::max, identity);
        final LongAccumulator accumulatorSum = new LongAccumulator(Long::sum, identity);

        generateStreamOfNumber(accumulatorMax);
        long maxExpected = 50L;
        assertEquals(maxExpected, accumulatorMax.get());

        long sumExpected = 150L;
        generateStreamOfNumber(accumulatorSum);
        assertEquals(sumExpected, accumulatorSum.get());

        // Same as above. The sum of stream calls the LongBinaryOperator Long::sum
        final long sum = Stream.of("10", "20", "30", "40", "50")
                .mapToLong(Long::parseLong)
                .sum();
        assertEquals(sumExpected, sum);
    }

    private void generateStreamOfNumber(LongAccumulator longAccumulator) {
        LongStream.rangeClosed(1, 5)
                .map(number -> number * 10)
                .forEach(longAccumulator::accumulate);
    }
}