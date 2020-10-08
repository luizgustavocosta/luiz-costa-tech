package tech.costa.luiz.cache.lfu;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * The type Least frequently used test.
 */
class LeastFrequentlyUsedTest {

    /**
     * The Capacity.
     */
    int capacity = 3;

    /**
     * Remove the less accessed.
     */
    @Test
    void remove_the_less_accessed() {
        LeastFrequentlyUsed<LeastFrequentlyUsed.News, LeastFrequentlyUsed.SocialMedia> cache = new LeastFrequentlyUsed<>(capacity);

        final LeastFrequentlyUsed.News messiAndMarca = new LeastFrequentlyUsed.News("Messi reach 800 goals", "Marca");
        final LeastFrequentlyUsed.News normalLifeElPais = new LeastFrequentlyUsed.News("The normal life restart today", "El País");
        final LeastFrequentlyUsed.News springElPais = new LeastFrequentlyUsed.News("Spring starts today", "El País");

        cache.put(messiAndMarca, new LeastFrequentlyUsed.SocialMedia("Twitter"));
        cache.put(normalLifeElPais, new LeastFrequentlyUsed.SocialMedia("Facebook"));
        cache.put(springElPais, new LeastFrequentlyUsed.SocialMedia("Instagram"));


        cache.get(messiAndMarca);
        cache.get(messiAndMarca);
        cache.get(springElPais);

        final LeastFrequentlyUsed.News ronaldoAs = new LeastFrequentlyUsed.News("Ronaldo wins the Ballon D'or", "As");

        cache.put(ronaldoAs, new LeastFrequentlyUsed.SocialMedia("Instagram"));

        assertThat(3, is(equalTo(cache.size())));

    }
}