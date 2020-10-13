package tech.costa.luiz.cache.mru;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * The type More recently used.
 * Applicability
 * https://stackoverflow.com/questions/5088128/why-does-cache-use-most-recently-used-mru-algorithm-as-evict-policy
 * https://searchstorage.techtarget.com/definition/cache-algorithm
 *
 * @param <T> the type parameter
 */
public class MoreRecentlyUsed<T> {

    private final int cacheSize;
    private final Set<T> cache;
    private final LinkedList<T> items;

    /**
     * Instantiates a new More recently used.
     *
     * @param cacheSize the cache size
     */
    public MoreRecentlyUsed(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cache = new LinkedHashSet<>(cacheSize);
        this.items = new LinkedList<>();
    }

    /**
     * Invalidate.
     *
     * @param type the type
     */
    public void invalidate(T type) {
        items.remove(type);
        cache.remove(type);
    }

    /**
     * Discard.
     */
    public void discard() {
        final T remove = items.remove(getLastIndex());
        cache.remove(remove);
    }

    private int getLastIndex() {
        return items.size() - 1;
    }

    /**
     * Add more recently used.
     *
     * @param type the type
     * @return the more recently used
     */
    public MoreRecentlyUsed add(T type) {
        if (cacheSize == items.size()) {
            // Limit reached
            this.discard();
        }
        items.add(nextIndex(), type);
        cache.add(type);
        return this;
    }

    private int getIndex() {
        return items.isEmpty() ? 0 : items.size() - 1;
    }

    private int nextIndex() {
        return items.isEmpty() ? 0 : items.size();
    }

    /**
     * Get t.
     *
     * @param key the key
     * @return the t
     */
    public T get(T key) {
        // Once is requested, the object is sent to tail
        if (items.remove(key)) {
            //list.add(getIndex(), map.get(key));
            items.addLast(key);
            return cache.stream()
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
                ",\n list=" + items +
                '}';
    }

    /**
     * Size int.
     *
     * @param <T> the type parameter
     * @return the int
     */
    public <T> int size() {
        return cache.size();
    }
}
