package Hash;

import java.util.*;

/**
 * Created by TheKingRing on 2016/11/19.
 */
public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;

    public LRUCache(int capacity) {
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }

    public int get(int key) {
        if (map.containsKey(key)) return map.get(key);
        return -1;
    }

    public void set(int key, int value) {
        map.put(key, value);
    }

}
