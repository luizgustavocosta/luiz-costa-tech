package tech.costa.luiz.cache.lfu;

import tech.costa.luiz.cache.frequency.CacheStrategy;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Example from https://www.programmersought.com/article/2064658867/
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LeastFrequentlyUsed <K, V> implements CacheStrategy<K, V> {

    private final int capacity;
    private final Map<K, V> cache = new HashMap<>();
    private final Map<K, CountItem> countItemMap = new HashMap<>();

    /**
     * Instantiates a new Least frequently used.
     *
     * @param capacity the capacity
     */
    public LeastFrequentlyUsed(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    @Override
    public void put(K key, V value) {
        V v = cache.get(key);
        if (isNull(v)) {
            if (capacity == cache.size()) {
                removeElement();
            }
            countItemMap.put(key, new CountItem(key, System.currentTimeMillis()));
        } else {
            addHitCount(key);
        }
        cache.put(key, value);
    }

    /**
     * Get v.
     *
     * @param key the key
     * @return the v
     */
    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (nonNull(value)) {
            addHitCount(key);
            return value;
        }
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

    /**
     * Remove element.
     */
    private void removeElement() {
        CountItem countItem = Collections.min(countItemMap.values());
        cache.remove(countItem.key);
        countItemMap.remove(countItem.key);
    }

    /**
     * Add hit count.
     * For each access increment the time and set the time
     * @param key the key
     */
    private void addHitCount(K key) {
        CountItem countItem = countItemMap.get(key);
        countItem.count = countItem.count + 1;
        countItem.lastTime = System.currentTimeMillis();
    }

    /**
     * Gets cache.
     *
     * @return the cache
     */
    @Override
    public Map<K, V> getCache() {
        return cache;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return "LeastFrequentlyUsed{" +
                "capacity=" + capacity +
                ", cache=" + cache +
                ", countItemMap=" + countItemMap.size() +
                '}';
    }

    /**
     * The type Count item.
     */
    private class CountItem implements Comparable<CountItem> {
        private final K key;
        private int count;
        private long lastTime;

        private CountItem(K key, long lastAccess) {
            this.key = key;
            this.count = 1;
            this.lastTime = lastAccess;
        }

        @Override
        public int compareTo(CountItem other) {
            int compare = Integer.compare(this.count, other.count);
            return compare == 0 ? Long.compare(this.lastTime, other.lastTime) : compare;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CountItem countItem = (CountItem) o;
            return count == countItem.count &&
                    lastTime == countItem.lastTime &&
                    key.equals(countItem.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, count, lastTime);
        }
    }
}
