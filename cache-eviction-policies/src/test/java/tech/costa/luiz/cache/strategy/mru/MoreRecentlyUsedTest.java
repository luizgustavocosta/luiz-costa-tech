package tech.costa.luiz.cache.strategy.mru;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.domain.Player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static tech.costa.luiz.cache.dataset.PlayerDataSet.*;

/**
 * The type More recently used test.
 */
@DisplayName("MRU")
class MoreRecentlyUsedTest {

    /**
     * Discard first cached element.
     */
    @DisplayName("Discard first cached element")
    @Test
    void discard_first_cached_element() {
        int cacheSize = 3;
        MoreRecentlyUsed<String, Player> cache = new MoreRecentlyUsed<>(cacheSize);

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

        // Then
        assertThat(cacheSize, is(equalTo(cache.size())));

        assertAll(() -> {
            assertNotNull(cache.get(terStegen.getId()));
            assertNotNull(cache.get(neto.getId()));
            assertNotNull(cache.get(messi.getId()));
            assertNull(cache.get(coutinho.getId()));
            assertNull(cache.get(ronaldinho.getId()));
        });
    }
}