package tech.costa.luiz.cache.strategy.lifo;

import tech.costa.luiz.cache.strategy.CacheStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Last in first out.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LastInFirstOut<K,V> implements CacheStrategy<K,V> {

    private final Map<K, V> cache;
    private final int cacheSize;
    private K newest;

    /**
     * Instantiates a new Last in first out.
     *
     * @param cacheSize the cache size
     */
    public LastInFirstOut(int cacheSize) {
        this.cacheSize = cacheSize;
        cache = new LinkedHashMap<>(cacheSize);
    }

    @Override
    public synchronized void  put(K key, V value) {
        if (cache.size() == cacheSize) {
            cache.remove(newest);
        }
        cache.put(key, value);
        newest = key;
    }

    @Override
    public V get(K key) {
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

    @Override
    public synchronized Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
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
    public String toString() {
        return "LastInFirstOut{" +
                "cache=" + cache +
                '}';
    }
}
