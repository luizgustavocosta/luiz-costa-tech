package tech.costa.luiz.cache.fifo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

class FirstInFirstOutTest {

    @Test
    @DisplayName("Given a cache size, use it as max elements")
    void should_respect_the_cache_size() {
        int cacheSize = 3;
        final FirstInFirstOut<String, String> fifoCache = new FirstInFirstOut<>(cacheSize);
        fifoCache.put("FCB", "John Doe");
        fifoCache.put("RMF", "Benzema");
        fifoCache.put("LFC", "Mane");
        fifoCache.put("FCB", "Messi");

        assertThat(cacheSize, is(equalTo(fifoCache.size())));
    }

}