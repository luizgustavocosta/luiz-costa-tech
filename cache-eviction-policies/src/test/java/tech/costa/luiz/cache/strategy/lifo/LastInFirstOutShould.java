package tech.costa.luiz.cache.strategy.lifo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
        LastInFirstOut<String, String> cache = new LastInFirstOut<>(cacheSize);

        cache.put("Forward", "Messi");
        cache.put("Midfield", "Coutinho");
        cache.put("Goalkeeper", "Neto");

        cache.put("LeftBack", "Roberto Carlos");

        assertThat(cacheSize, is(equalTo(cache.size())));

        final List<String> cacheListFormat = cache.getAll().stream()
                .map(entry -> String.join(":", entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        List<String> expected = Arrays.asList("Forward:Messi", "Midfield:Coutinho", "LeftBack:Roberto Carlos");
        assertThat(expected, is(equalTo(cacheListFormat)));
    }

}