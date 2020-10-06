package tech.costa.luiz.cache.lfu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Example from https://www.programmersought.com/article/2064658867/
 * @param <K>
 * @param <V>
 */
public class LeastFrequentlyUsed <K, V> {

    private final int capacity;

    private final Map<K, V> cache = new HashMap<>();

    private final Map<K, HitRate> count = new HashMap<>(); // TODO - Make it lambda expression

    public LeastFrequentlyUsed(int capacity) {
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        V v = cache.get(key);
        if (isNull(v)) {
            if (capacity == cache.size()) {
                removeElement();
            }
            count.put(key, new HitRate(key, 1, System.nanoTime()));
        } else {
            addHitCount(key);
        }
        cache.put(key, value);
    }

    public V get(K key) {
        V value = cache.get(key);
        if (nonNull(value)) {
            addHitCount(key);
            return value;
        }
        return null;
    }

    private void removeElement() {
        HitRate hr = Collections.min(count.values());
        cache.remove(hr.key);
        count.remove(hr.key);
    }

    private void addHitCount(K key) {
        HitRate hitRate = count.get(key);
        hitRate.hitCount = hitRate.hitCount + 1;
        hitRate.lastTime = System.nanoTime();
    }

    public Map<K, V> getCache() {
        return cache;
    }

    //Internal class
    private class HitRate implements Comparable<HitRate> {
        private K key;
        private int hitCount;
        private long lastTime;

        private HitRate(K key, int hitCount, long lastTime) {
            this.key = key;
            this.hitCount = hitCount;
            this.lastTime = lastTime;
        }

        @Override
        public int compareTo(HitRate other) {
            int compare = Integer.compare(this.hitCount, other.hitCount);
            return compare == 0 ? Long.compare(this.lastTime, other.lastTime) : compare;
        }
    }

}
