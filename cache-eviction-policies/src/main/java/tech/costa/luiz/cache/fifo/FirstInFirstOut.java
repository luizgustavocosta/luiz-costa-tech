package tech.costa.luiz.cache.fifo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Example from https://www.programmersought.com/article/35722271759/

/**
 * The type First in first out.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class FirstInFirstOut<K,V> {

    private final int maxSize;
    private final float loadFactory = 0.75f;
    /**
     * When false works as FIFO
     * */
    private final boolean accessOrder = false;
    private final Map<K, V> map;

    /**
     * Instantiates a new First in first out.
     *
     * @param cacheSize the cache size
     */
    public FirstInFirstOut(int cacheSize) {
        maxSize = cacheSize;
        int capacity = (int) Math.ceil(maxSize / loadFactory) + 1;
        map = new LinkedHashMap<K, V>(capacity, loadFactory, accessOrder) {
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
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    /**
     * Get v.
     *
     * @param key the key
     * @return the v
     */
    public synchronized V get(K key) {
        return map.get(key);
    }

    /**
     * Remove.
     *
     * @param key the key
     */
    public synchronized void remove(K key) {
        map.remove(key);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return map
                .entrySet()
                .stream()
                .map(entryKey -> String.format("%s: %s%n", entryKey.getKey(), entryKey.getValue()))
                .collect(Collectors.joining());
    }
}
