package tech.costa.luiz.cache.mru;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * The type More recently used.
 * Applicability
 * https://stackoverflow.com/questions/5088128/why-does-cache-use-most-recently-used-mru-algorithm-as-evict-policy
 * https://searchstorage.techtarget.com/definition/cache-algorithm
 * @param <K> the type parameter
 * @param <V> the type parameter
 *
 */
public class MoreRecentlyUsed<T> {

    private final int cacheSize;
    private final Set<T> map;
    private final LinkedList<T> items;

    /**
     * Instantiates a new More recently used.
     *
     * @param cacheSize the cache size
     */
    public MoreRecentlyUsed(int cacheSize) {
        this.cacheSize = cacheSize;
        this.map = new LinkedHashSet<>(cacheSize);
        this.items = new LinkedList<>();
    }

    public void invalidate(T type) {
        items.remove(type);
        map.remove(type);
    }

    public void discard() {
        final T remove = items.remove(getLastIndex());
        map.remove(remove);
    }

    private int getLastIndex() {
        return items.size() - 1;
    }

    public MoreRecentlyUsed add(T type) {
        if (cacheSize == items.size()) {
            // Limit reached
            this.discard();
        }
        items.add(nextIndex(), type);
        map.add(type);
        return this;
    }

    private int getIndex() {
        return items.isEmpty() ? 0 : items.size() - 1;
    }

    private int nextIndex() {
        return items.isEmpty() ? 0 : items.size();
    }

    public T get(T key) {
        // Once is requested, the object is sent to tail
        if (items.remove(key)) {
            //FIXME list.addLast(key);
            //list.add(getIndex(), map.get(key));
            items.addLast(key);
            return map.stream()
                    .filter(currentType -> currentType.equals(key))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String toString() {
        return "MoreRecentlyUsed{" +
                "\ncacheSize=" + cacheSize +
                //", map=" + map +
                ",\n list=" + items +
                '}';
    }

    static class Music {
        private final String name;
        private final String album;
        private final String author;


        Music(String name, String album, String author) {
            this.name = name;
            this.album = album;
            this.author = author;
        }

        @Override
        public String toString() {
            return "\nMusic{" +
                    "name='" + name + '\'' +
                    /*", album='" + album + '\'' +
                    ", author='" + author + '\'' +*/
                    '}';
        }
    }

}
