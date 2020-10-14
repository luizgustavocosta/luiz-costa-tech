package tech.costa.luiz.cache.strategy.lfu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * The type Least frequently used test.
 */
@DisplayName("LFU")
class LeastFrequentlyUsedTest {

    /**
     * The Capacity.
     */

    int id = 0;

    String generateId(){
        return String.valueOf(id++);
    }

    /**
     * Remove the less accessed.
     */
    @DisplayName("Counts how often an item is needed. Those that are used least often are discarded first.")
    @Test
    void remove_the_less_accessed() {
        // Given
        int capacity = 3;
        LeastFrequentlyUsed<String, String> cache = new LeastFrequentlyUsed<>(capacity);
        final String ronaldinho = "Ronaldinho", neto = "neto",
                messi = "Messi", coutinho = "coutinho",
                terStegen = "Ter stegen";

        String idCoutinho = generateId();
        String idNeto = generateId();
        String idMessi = generateId();

        cache.put(idCoutinho, coutinho);
        cache.put(idNeto, neto);
        cache.put(idMessi, messi);

        // When
        cache.get(idMessi);
        cache.get(idMessi);
        cache.get(idCoutinho);

        String idRonaldinho = generateId();
        String idTerStegen = generateId();

        cache.put(idRonaldinho, ronaldinho);
        cache.get(idNeto);
        cache.put(idTerStegen, terStegen);

        int actualExpected = 3;

        // Then
        final int countSize = cache.size();

        assertThat(actualExpected, is(equalTo(cache.size())));
        assertThat(countSize, is(equalTo(cache.size())));

        final Map<String, String> currentCache = cache.getCache();

        assertAll(() -> {
            assertThat(null, is(equalTo(cache.get(idNeto))));
            assertThat(null, is(equalTo(cache.get(idRonaldinho))));

            assertThat(currentCache, hasKey(idCoutinho));
            assertThat(currentCache, hasKey(idMessi));
            assertThat(currentCache, hasKey(idTerStegen));
        });
    }
}