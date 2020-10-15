package tech.costa.luiz.cache.strategy.lru;

import tech.costa.luiz.cache.strategy.CacheStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Least recently used.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LeastRecentlyUsed<K,V> implements CacheStrategy<K,V> {

    private final int maxSize;
    private static final float LOAD_FACTORY = 0.75f;
    private final Map<K, V> cache;

    /**
     * Instantiates a new Least recently used.
     *
     * @param cacheSize the cache size
     */
    public LeastRecentlyUsed(int cacheSize) {
        maxSize = cacheSize;
        /**
         *  Capacity is the amount of space that the object is currently using
         */
        int capacity = (int)Math.ceil(maxSize / LOAD_FACTORY) + 1;
        /*
         * true, the representative LinkedList sorted in order of access, it can be used as a cache LRU
         * false, for insertion sort order, as FIFO buffer
         */
        cache = new LinkedHashMap<K, V>(capacity, LOAD_FACTORY, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public Map<K, V> getCache() {
        return cache;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

    @Override
    public String toString() {
        return "LeastRecentlyUsed{" +
                "maxSize=" + maxSize +
                ", cache=" + cache +
                '}';
    }
}
