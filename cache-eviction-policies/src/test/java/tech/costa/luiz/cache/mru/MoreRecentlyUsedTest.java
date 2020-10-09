package tech.costa.luiz.cache.mru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static tech.costa.luiz.cache.mru.MoreRecentlyUsed.*;

class MoreRecentlyUsedTest {

    MoreRecentlyUsed<Music> cacheMRU;
    Music fimDeSemanaNoParque, likeARollingStone, imagine;
    int cacheSize = 3;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        cacheMRU = new MoreRecentlyUsed<>(cacheSize);
        fimDeSemanaNoParque = new Music("Fim de semana no parque", "Raio-X do Brasil", "Racionais Mc's");
        likeARollingStone = new Music("Like a Rolling Stone", "Highway 61 Revisited", "Bob Dylan");
        imagine = new Music("Imagine", "Imagine", "John Lennon");

        cacheMRU.add(fimDeSemanaNoParque)
                .add(likeARollingStone)
                .add(imagine);

    }

    @Test
    void invalidate() {
    }

    @Test
    void printKeyOrder() {
        System.out.println(cacheMRU.toString());
    }

    @Test
    void prune() {
    }

    @Test
    void put() {
    }

    @Test
    void get() {
        final Music music = cacheMRU.get(likeARollingStone);
        System.out.println(cacheMRU);

    }
}