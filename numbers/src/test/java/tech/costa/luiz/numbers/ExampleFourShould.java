package tech.costa.luiz.numbers;

import com.google.common.collect.Streams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ExampleFourShould {

    AtomicInteger seed = new AtomicInteger(1);

    @DisplayName("Be safe")
    @Test
    void workAnAtomicWay() {

    }

    @Test
    public void testUnsubscribeOnNestedTakeAndSyncInfiniteStream() throws InterruptedException {



        Stream<String> streamA = IntStream.range(0, 100000).mapToObj(String::valueOf).parallel();
        Stream<Integer> streamB = IntStream.range(0, 100000).boxed().parallel();
        AtomicInteger count = new AtomicInteger(0);
        Streams.forEachPair(
                streamA,
                streamB,
                (a, b) -> {
                    count.incrementAndGet();
                    final boolean equals = a.equals(String.valueOf(b));
                    if (!equals) {
                        System.out.println("Not Equals "+ a + b);
                    }
                });
    }



}