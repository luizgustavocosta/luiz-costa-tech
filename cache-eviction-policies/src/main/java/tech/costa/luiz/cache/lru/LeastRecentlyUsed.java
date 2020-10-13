package tech.costa.luiz.cache.lru;

import tech.costa.luiz.cache.frequency.CacheStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LeastRecentlyUsed<K,V> implements CacheStrategy<K,V> {

    private final int maxSize;
    private static final float LOAD_FACTORY = 0.75f;
    private final Map<K, V> cache;

    public LeastRecentlyUsed(int cacheSize) {
        maxSize = cacheSize;
        int capacity = (int)Math.ceil(maxSize / LOAD_FACTORY) + 1; // Explain better way using this.
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

    private void remove(K key) {
        //FIXME
        cache.remove(key);
    }

    @Override
    public Map<K, V> getCache() {
        return cache;
    }

    @Override
    public Set<Map.Entry<K, V>> getAll() {
        return cache.entrySet();
    }

    @Override
    public String toString() {
        return cache.entrySet().stream()
                .map((element) -> String.format("%s: %s", element.getKey(), element.getValue()))
                .collect(Collectors.joining());
    }
}
