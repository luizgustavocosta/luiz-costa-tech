package tech.costa.luiz.cache.strategy.fifo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static tech.costa.luiz.cache.dataset.PlayerDataSet.*;

/**
 * The type First in first out test.
 */
@DisplayName("FIFO")
class FirstInFirstOutTest {

    int cacheSize = 3;
    FirstInFirstOut<String, Player> cache;
    Player coutinho = getCoutinho(),
            neto = getNeto(),
            messi = getMessi(),
            ronaldinho = getRonaldinho(),
            terStegen = getTerStegen();

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        cache = new FirstInFirstOut<>(cacheSize);
        cache.put(coutinho.getId(), coutinho);
        cache.put(neto.getId(), neto);
        cache.put(messi.getId(), messi);
        cache.put(ronaldinho.getId(), ronaldinho);
        cache.put(terStegen.getId(), terStegen);
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
                .map(entrySet -> String.format("%s:%s", entrySet.getKey(), entrySet.getValue().getName()))
                .collect(Collectors.toList());

        List<String> expectedLines = Arrays.asList("8:Messi", "9:Ronaldinho", "10:Ter stegen");
        assertLinesMatch(expectedLines, actualLines);
    }

}