package tech.costa.luiz.cache.strategy.lfu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.domain.Player;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static tech.costa.luiz.cache.dataset.PlayerDataSet.*;

/**
 * The type Least frequently used test.
 */
@DisplayName("LFU")
class LeastFrequentlyUsedTest {

    /**
     * Remove the less accessed.
     */
    @DisplayName("Counts how often an item is needed. Those that are used least often are discarded first.")
    @Test
    void remove_the_less_accessed() {
        // Given
        int capacity = 3;
        LeastFrequentlyUsed<String, Player> cache = new LeastFrequentlyUsed<>(capacity);

        // Given
        Player coutinho = getCoutinho();
        Player neto = getNeto();
        Player messi = getMessi();

        cache.put(coutinho.getId(), coutinho);
        cache.put(neto.getId(), neto);
        cache.put(messi.getId(), messi);

        // When
        cache.get(messi.getId());
        cache.get(messi.getId());
        cache.get(coutinho.getId());

        Player ronaldinho = getRonaldinho();
        Player terStegen = getTerStegen();

        cache.put(ronaldinho.getId(), ronaldinho);
        cache.put(terStegen.getId(), terStegen);

        int actualExpected = 3;

        // Then
        final int countSize = cache.size();

        assertThat(actualExpected, is(equalTo(cache.size())));
        assertThat(countSize, is(equalTo(cache.size())));

        final Map<String, Player> currentCache = cache.getCache();

        assertAll(() -> {
            assertThat(null, is(equalTo(cache.get(neto.getId()))));
            assertThat(null, is(equalTo(cache.get(ronaldinho.getId()))));

            assertThat(currentCache, hasKey(coutinho.getId()));
            assertThat(currentCache, hasKey(messi.getId()));
            assertThat(currentCache, hasKey(terStegen.getId()));
        });
    }
}