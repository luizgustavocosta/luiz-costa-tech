package tech.costa.luiz.cache.fifo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * The type First in first out test.
 */
class FirstInFirstOutTest {

    /**
     * The Fifo cache.
     */
    FirstInFirstOut<String, String> fifoCache;
    /**
     * The Cache size.
     */
    int cacheSize = 3;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        fifoCache = new FirstInFirstOut<>(cacheSize);
        fifoCache.put("9", "Suarez");
        fifoCache.put("10", "Messi");
        fifoCache.put("8", "Rakitic");
        fifoCache.put("8", "Pjanić");
        fifoCache.put("9", "Ronaldo");
    }

    /**
     * Should respect the cache size.
     */
    @Test
    @DisplayName("Respect the cache size")
    void should_respect_the_cache_size() {
        assertThat(cacheSize, is(equalTo(fifoCache.size())));
    }

    /**
     * Should remove elements when reach the limit.
     */
    @Test
    @DisplayName("Remove the oldest elements when reach the limit")
    void should_remove_elements_when_reach_the_limit() {
        final List<String> actualLines = fifoCache.getAll().stream()
                .map(entrySet -> String.format("%s:%s", entrySet.getKey(), entrySet.getValue()))
                .collect(Collectors.toList());

        List<String> expectedLines = Arrays.asList("9:Ronaldo","10:Messi", "8:Pjanić");
        assertLinesMatch(expectedLines, actualLines);
    }

}