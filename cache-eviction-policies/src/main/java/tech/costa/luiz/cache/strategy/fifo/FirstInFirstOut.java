package tech.costa.luiz.cache.strategy.fifo;

import tech.costa.luiz.cache.strategy.CacheStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Example from https://www.programmersought.com/article/35722271759/
/**
 * The type First in first out.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class FirstInFirstOut<K,V> implements CacheStrategy<K,V> {

    private final int maxSize;
    private final Map<K, V> cache;

    /**
     * Instantiates a new First in first out.
     *
     * @param cacheSize the cache size
     */
    public FirstInFirstOut(int cacheSize) {
        this.maxSize = cacheSize;
        /**
         * When false works as FIFO (First In First Out)
         * */
        cache = new LinkedHashMap<K, V>(cacheSize, 0.75f,false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    @Override
    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    /**
     * Get v.
     *
     * @param key the key
     * @return the v
     */
    @Override
    public synchronized V get(K key) {
        return cache.get(key);
    }

    /**
     * Remove.
     *
     * @param key the key
     */
    public synchronized void remove(K key) {
        cache.remove(key);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @Override
    public synchronized Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

    /**
     *  Get Cache
     * @return Map
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
    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return "FirstInFirstOut{" +
                "maxSize=" + maxSize +
                ", cache=" + cache +
                '}';
    }
}
