package tech.costa.luiz.cache.strategy.fifo;

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
@DisplayName("FIFO")
class FirstInFirstOutTest {

    /**
     * The Fifo cache.
     */
    FirstInFirstOut<String, String> cache;
    /**
     * The Cache size.
     */
    int cacheSize = 3;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        cache = new FirstInFirstOut<>(cacheSize);

        cache.put("Midfield", "Coutinho");
        cache.put("Goalkeeper", "Neto");
        cache.put("Forward", "Messi");
        cache.put("Midfield", "Ronaldinho");
        cache.put("Goalkeeper", "Ter stegen");
    }

    /**
     * Should respect the cache size.
     */
    @Test
    @DisplayName("Respect the cache size")
    void should_respect_the_cache_size() {
        assertThat(cacheSize, is(equalTo(cache.size())));
    }

    /**
     * Should remove elements when reach the limit.
     */
    @Test
    @DisplayName("Remove the oldest elements when reach the limit")
    void should_remove_elements_when_reach_the_limit() {
        final List<String> actualLines = cache.getAll().stream()
                .map(entrySet -> String.format("%s:%s", entrySet.getKey(), entrySet.getValue()))
                .collect(Collectors.toList());

        List<String> expectedLines = Arrays.asList("Midfield:Ronaldinho", "Goalkeeper:Ter stegen", "Forward:Messi");
        assertLinesMatch(expectedLines, actualLines);
    }

}