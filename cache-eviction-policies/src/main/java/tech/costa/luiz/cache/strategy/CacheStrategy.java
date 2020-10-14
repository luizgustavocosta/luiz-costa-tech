package tech.costa.luiz.cache.strategy;

import java.util.Map;
import java.util.Set;

/**
 * The interface Cache strategy.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public interface CacheStrategy<K,V> {

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Get v.
     *
     * @param key the key
     * @return the v
     */
    V get(K key);

    /**
     * Gets all.
     *
     * @return the all
     */
    Set<Map.Entry<K, V>> getAll();

    /**
     * Gets cache.
     *
     * @return the cache
     */
    Map<K, V> getCache();

    /**
     * Size int.
     *
     * @return the int
     */
    int size();

}
