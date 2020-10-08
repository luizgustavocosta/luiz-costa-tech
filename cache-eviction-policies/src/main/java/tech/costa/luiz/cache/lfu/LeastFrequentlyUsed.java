package tech.costa.luiz.cache.lfu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Example from https://www.programmersought.com/article/2064658867/
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class LeastFrequentlyUsed <K, V> {

    private final int capacity;

    private final Map<K, V> cache = new HashMap<>();

    private final Map<K, CountItem> countItemMap = new HashMap<>();

    /**
     * Instantiates a new Least frequently used.
     *
     * @param capacity the capacity
     */
    public LeastFrequentlyUsed(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        V v = cache.get(key);
        if (isNull(v)) {
            if (capacity == cache.size()) {
                removeElement();
            }
            countItemMap.put(key, new CountItem(key, 1, System.currentTimeMillis()));
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
    public V get(K key) {
        V value = cache.get(key);
        if (nonNull(value)) {
            addHitCount(key);
            return value;
        }
        return null;
    }

    private void removeElement() {
        CountItem countItem = Collections.min(countItemMap.values());
        cache.remove(countItem.key);
        countItemMap.remove(countItem.key);
    }

    private void addHitCount(K key) {
        CountItem countItem = countItemMap.get(key);
        countItem.count = countItem.count++;
        countItem.lastTime = System.currentTimeMillis();
    }

    /**
     * Gets cache.
     *
     * @return the cache
     */
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
                "capacity=" + capacity +
                ", cache=" + cache +
                ", countItemMap=" + countItemMap +
                '}';
    }

    private class CountItem implements Comparable<CountItem> {
        private K key;
        private int count;
        private long lastTime;

        private CountItem(K key, int count, long lastAccess) {
            this.key = key;
            this.count = count;
            this.lastTime = lastAccess;
        }

        @Override
        public int compareTo(CountItem other) {
            int compare = Integer.compare(this.count, other.count);
            return compare == 0 ? Long.compare(this.lastTime, other.lastTime) : compare;
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
    }

    /**
     * The type News.
     */
    static class News {
        private String title;
        private String source;

        /**
         * Instantiates a new News.
         *
         * @param title  the title
         * @param source the source
         */
        public News(String title, String source) {
            this.title = title;
            this.source = source;
        }

        /**
         * Gets title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets title.
         *
         * @param title the title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Gets source.
         *
         * @return the source
         */
        public String getSource() {
            return source;
        }

        /**
         * Sets source.
         *
         * @param source the source
         */
        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "News{" +
                    "title='" + title + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }

    /**
     * The type Social media.
     */
    static class SocialMedia {

        private final String name;

        /**
         * Instantiates a new Social media.
         *
         * @param name the name
         */
        public SocialMedia(String name) {
            this.name = name;
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "SocialMedia{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
