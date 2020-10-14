package tech.costa.luiz.cache.strategy.mru;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.dataset.NewsDataSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type More recently used test.
 */
@DisplayName("MRU")
class MoreRecentlyUsedTest {

    int id = 0;
    /**
     * Discard first cached element.
     */
    @DisplayName("Discard first cached element")
    @Test
    void discard_first_cached_element() {
        int cacheSize = 3;
        MoreRecentlyUsed<String, String> cache = new MoreRecentlyUsed<>(cacheSize);
        // Given
        final NewsDataSet dataSet = NewsDataSet.getInstance();

        final String ronaldinho = "Ronaldinho", neto = "neto",
                messi = "Messi", coutinho = "coutinho",
                terStegen = "Ter stegen";

        final String midfield = "midfield", goalkeeper = "goalkeeper", forward = "Forward";

        String idCoutinho = generateId();
        String idNeto = generateId();
        String idMessi = generateId();

        cache.put(idCoutinho, coutinho);
        cache.put(idNeto, neto);
        cache.put(idMessi, messi);

        // When
        String idRonaldinho = generateId();
        String idTerStegen = generateId();

        cache.put(idRonaldinho, ronaldinho);
        cache.get(idNeto);
        cache.put(idTerStegen, terStegen);

        // Then
        assertThat(cacheSize, is(equalTo(cache.size())));

        assertAll(() -> {
            assertNotNull(cache.get(idCoutinho));
            assertNotNull(cache.get(idRonaldinho));
            assertNotNull(cache.get(idTerStegen));
            assertNull(cache.get(idMessi));
            assertNull(cache.get(idNeto));
        });
    }

    String generateId(){
        return String.valueOf(id++);
    }
}