package tech.costa.luiz.numbers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleFourShould {
    AtomicInteger seed;
    int otherSeed;
    @BeforeAll
    void setUp() {
        seed = new AtomicInteger(0);
    }


    @DisplayName("Be safe")
    @Test
    void workAnAtomicWay() {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                fakeMethod();
                System.out.println(seed.get()+" - Thread name - "+Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        System.out.println("Seed ->"+seed.get()+"/ otherSeed ->"+otherSeed);
    }

    private void fakeMethod() {
        try {
            sleep(50);
            seed.incrementAndGet();
            otherSeed += 1;
        } catch (InterruptedException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }
}