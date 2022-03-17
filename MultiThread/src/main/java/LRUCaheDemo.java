import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap非常适合用来做LRU caches
 */
public class LRUCaheDemo<K,V> extends LinkedHashMap<K,V> {

    private int capacity;

    public LRUCaheDemo(int capacity) {
        super(capacity,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCaheDemo lruCaheDemo = new LRUCaheDemo(2);
        lruCaheDemo.put(1, 1); // 缓存是 {1=1}
        lruCaheDemo.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lruCaheDemo.get(1));    // 返回 1
        lruCaheDemo.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lruCaheDemo.get(2));    // 返回 -1 (未找到)
        lruCaheDemo.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lruCaheDemo.get(1));   // 返回 -1 (未找到)
        System.out.println(lruCaheDemo.get(3));    // 返回 3
        System.out.println(lruCaheDemo.get(4));    // 返回 4


    }

}
