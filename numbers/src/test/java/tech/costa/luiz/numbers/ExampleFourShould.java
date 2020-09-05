package tech.costa.luiz.numbers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Example four should.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleFourShould {

    private final AtomicInteger safeNumberType = new AtomicInteger();
    private int unsafeNumberType;

    /**
     * Work an atomic way.
     */
    @DisplayName("Working with atomic type number")
    @Test
    void workAnAtomicWay() {
        short numberOfThreads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        byte startInclusive = 1;
        byte endInclusive = 10;

        IntStream.rangeClosed(startInclusive, endInclusive)
                .forEach(value ->
                        executorService.execute(this::fakeMethod));

        executorService.shutdown();

        while (true) {
            if (executorService.isTerminated()) break;
        }
        int expectedAtomicIncremental = 10;
        assertEquals(expectedAtomicIncremental, safeNumberType.get(),
                String.format("%s%3d","The number expected is",expectedAtomicIncremental));
        assertTrue(unsafeNumberType != expectedAtomicIncremental,
                String.format("%s%3d","The number expected is something different from",expectedAtomicIncremental));
    }

    /**
     * Fake method use to simulate a system processing
     */
    private void fakeMethod() {
        try {
            sleep(50);
            safeNumberType.incrementAndGet();
            unsafeNumberType += 1;
        } catch (InterruptedException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }
}