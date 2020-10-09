package tech.costa.luiz.cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LeastRecentlyUsed<K,V> {

    private int MAX_SIZE;
    private final float LOAD_FACTORY = 0.75f;

    private Map<K, V> map;

    public LeastRecentlyUsed(int cacheSize) {
        MAX_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_SIZE / LOAD_FACTORY) + 1; // Explain better way using this.
        /*
         * The third parameter is set to true, the representative linkedlist sorted in order of access, it can be used as a cache LRU
         * The third parameter is set to false, for insertion sort order, as FIFO buffer
         */
        map = new LinkedHashMap<K, V>(capacity, LOAD_FACTORY, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        return map.entrySet().stream()
                .map((element) -> String.format("%s: %s", element.getKey(), element.getValue()))
                .collect(Collectors.joining());
    }
}
