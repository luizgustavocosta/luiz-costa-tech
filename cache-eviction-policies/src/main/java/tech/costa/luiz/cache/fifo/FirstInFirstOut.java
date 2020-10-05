package tech.costa.luiz.cache.fifo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Example from https://www.programmersought.com/article/35722271759/

public class FirstInFirstOut<K,V> {

    private int MAX_SIZE;
    private final float LOAD_FACTORY = 0.75f;

    private Map<K, V> map;

    public FirstInFirstOut(int cacheSize) {
        MAX_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_SIZE / LOAD_FACTORY) + 1; // TODO Explain better way using this.
        /*
         * The third parameter is set to true, the representative linkedlist sorted in order of access, it can be used as a cache LRU
         * The third parameter is set to false, for insertion sort order, as FIFO buffer
         */
        map = new LinkedHashMap<K, V>(capacity, LOAD_FACTORY, false) {
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

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        map
                .forEach((k, v) -> builder.append(String.format("%s: %s%n", k, v)));
        return builder.toString();
    }
}
