package tech.costa.luiz.cache.strategy.lifo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.costa.luiz.cache.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static tech.costa.luiz.cache.dataset.PlayerDataSet.*;

/**
 * The type Last in first out should.
 */
@DisplayName("LIFO")
class LastInFirstOutShould {

    /**
     * The cache evicts the block accessed most recently first without any regard to
     * how often or how many times it was accessed before.
     */
    @DisplayName("Evicts the block accessed most recently first without any regard how many times it was accessed before")
    @Test
    void evicts_most_recently_element() {
        int cacheSize = 3;
        LastInFirstOut<String, Player> cache = new LastInFirstOut<>(cacheSize);
        // Creating players
        Player coutinho = getCoutinho();
        Player neto = getNeto();
        Player messi = getMessi();
        Player ronaldinho = getRonaldinho();
        Player terStegen = getTerStegen();

        // Add the players to max list size
        cache.put(coutinho.getId(), coutinho);
        cache.put(neto.getId(), neto);
        cache.put(messi.getId(), messi);

        // Add new players
        cache.put(ronaldinho.getId(), ronaldinho);
        cache.put(terStegen.getId(), terStegen);

        assertThat(cacheSize, is(equalTo(cache.size())));

        final List<String> cacheListFormat = cache.getAll().stream()
                .map(entry -> String.join(",",entry.getValue().getName()))
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("Coutinho", "Neto", "Ter stegen");
        assertThat(expected, is(equalTo(cacheListFormat)));

        // Other way to do asserts
        assertAll(() -> {
            assertThat(cache.getCache(), hasValue(coutinho));
            assertThat(cache.getCache(), hasValue(neto));
            assertThat(cache.getCache(), hasValue(terStegen));
        });
    }
}