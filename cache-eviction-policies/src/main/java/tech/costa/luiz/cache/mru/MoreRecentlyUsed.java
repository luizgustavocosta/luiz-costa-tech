package tech.costa.luiz.cache.mru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MoreRecentlyUsed<K,V> {

    private int cacheSize;
    private HashMap<K,V> map;
    private LinkedList<V> list;

    public MoreRecentlyUsed(int cacheSize) {
        this.cacheSize = cacheSize;
        this.map = new LinkedHashMap();
        this.list = new LinkedList();
    }

    public void invalidate(Object key) {
        list.remove(key);
        map.remove(key);
    }

    public void printKeyOrder() {
        System.out.println("KeyOrder() " + list + " Cache content: " + map);
    }

    public void prune()// removes the tail
    {
        Object key = list.removeLast();
        map.remove(key);
    }

    public void put(K key, V val) {
        // check if pruning is needed
        if (list.size() == this.cacheSize) {
            this.prune();
        }
        list.addLast(val);
        map.put(key, val);
    }

    public V get(K key) {
        if (list.remove(key)) {
            //FIXME list.addLast(key);
            return map.get(key);
        }
        return null;
    }
}
