package tech.costa.luiz.cache.strategy.lfu;

import tech.costa.luiz.cache.strategy.CacheStrategy;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Example from https://www.programmersought.com/article/2064658867/
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LeastFrequentlyUsed <K, V> implements CacheStrategy<K, V> {

    private final int maxSize;
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Map<K, CountItem> countItemMap = new ConcurrentHashMap<>();

    /**
     * Instantiates a new Least frequently used.
     *
     * @param maxSize the capacity
     */
    public LeastFrequentlyUsed(int maxSize) {
        this.maxSize = maxSize;
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
            if (maxSize == cache.size()) {
                removeElement();
            }
            countItemMap.put(key, new CountItem(key, Clock.systemUTC().millis()));
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
     * Remove the element less requested
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
        countItem.lastTime = Clock.systemUTC().millis();
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
                "capacity=" + maxSize +
                ", cache=" + cache +
                ", countItemMap=" + countItemMap.size() +
                '}';
    }

    /**
     * Gets count item map.
     *
     * @return the count item map
     */
    public Map<K, CountItem> getCountItemMap() {
        return countItemMap;
    }

    /**
     * The type Count item.
     * Used for maintain the item more accessed in the map
     */
    class CountItem implements Comparable<CountItem> {
        private final K key;
        private int count;
        private long lastTime;

        private CountItem(K key, long lastAccess) {
            this.key = key;
            this.lastTime = lastAccess;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public K getKey() {
            return key;
        }

        @Override
        public int compareTo(CountItem other) {
            int count = Integer.compare(this.count, other.count);
            return count == 0 ? Long.compare(this.lastTime, other.lastTime) : count;
        }

        /**
         * Gets count.
         *
         * @return the count
         */
        public int getCount() {
            return count;
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

        @Override
        public String toString() {
            return "CountItem{" +
                    "key=" + key +
                    ", count=" + count +
                    '}';
        }
    }
}
