package tech.costa.luiz.cache.strategy.mru;

import tech.costa.luiz.cache.strategy.CacheStrategy;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type More recently used.
 * Remove the most recent used. Replace the top of stack
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class MoreRecentlyUsed<K, V> implements CacheStrategy<K,V> {

    private final int cacheSize;
    private final Map<K, V> cache;
    private final LinkedList<K> items;

    /**
     * Instantiates a new More recently used.
     *
     * @param cacheSize the cache size
     */
    public MoreRecentlyUsed(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cache = new ConcurrentHashMap<>(cacheSize);
        this.items = new LinkedList<>();
    }

    /**
     * Discard.
     */
    private void discard() {
        final K remove = items.removeLast();
        cache.remove(remove);
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    @Override
    public void put(K key, V value) {
        if (items.size() == cacheSize) {
            this.discard();
        }
        items.addLast(key);
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
        // Once is requested, the object is sent to head
        if (items.remove(key)) {
            items.addLast(key);
            return cache.get(key);
        }
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

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
        return "MoreRecentlyUsed{" +
                "maxSize=" + cacheSize +
                ", cache=" + cache +
                ", items=" + items +
                '}';
    }
}
