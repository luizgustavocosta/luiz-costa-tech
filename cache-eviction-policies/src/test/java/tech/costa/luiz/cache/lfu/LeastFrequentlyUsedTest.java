package tech.costa.luiz.cache.lfu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeastFrequentlyUsedTest {

    @Test void removeTheLeastNewLessUsed() {
        int capacity = 3;
        LeastFrequentlyUsed<Integer, Integer> cache = new LeastFrequentlyUsed<>(capacity);
        // Add as annotation
        cache.put(2, 2);
        cache.put(1, 1);

        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

        cache.put(3, 3);
        cache.put(4, 4);

        //1, 2 elements have access times, after 3, the cache is full, when 4 is added, 3 is eliminated.
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        //System.out.println(cache.get(1));
        System.out.println(cache.get(4));

        cache.put(5, 5);
        // Currently 2 visits 2 times, 1 visit once, 4 visits once, since the time of 4 is relatively new, 1 element is removed when 5 is placed.
        System.out.println("-=-=-=-");
        cache.getCache().entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });


    }

}