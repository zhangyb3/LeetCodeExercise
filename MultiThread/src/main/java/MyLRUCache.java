import java.util.HashMap;
import java.util.Map;

class Node<K,V> {
    K key;
    V value;
    Node<K,V> prev;
    Node<K,V> next;

    public Node() {
        this.prev = null;
        this.next = null;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;

    }
}

class DoubleLinkedList<K,V> {
    Node<K,V> head;
    Node<K,V> tail;

    public DoubleLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 头插
     */
    public void headAdd(Node<K,V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 删除节点
     */
    public void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    /**
     * 最后节点
     */
    public Node<K,V> getLast() {
        return tail.prev;
    }
}

public class MyLRUCache {

    private int capacity;
    Map<Integer,Node<Integer,Integer>> cache;
    DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public MyLRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }else {
            Node<Integer,Integer> node = cache.get(key);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.headAdd(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node<Integer,Integer> node = cache.get(key);
            node.value = value;
            cache.put(key,node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.headAdd(node);

        }else {
            if(cache.size() == capacity) {
                Node<Integer,Integer> lastNode = doubleLinkedList.getLast();
                cache.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }

            Node<Integer,Integer> newNode = new Node<>(key,value);
            cache.put(key,newNode);
            doubleLinkedList.headAdd(newNode);
        }
    }

}
