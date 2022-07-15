package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private final int INITIAL_CAPACITY = 16; //NUMBER_OF_BUCKETS;
    private final double LOAD_FACTOR = 0.75; // NUMBER_OF_ELEMENTS/NUMBER_OF_BUCKETS


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private double maxLoadFactor;
    private int capacity;

    /** Constructors */
    public MyHashMap() {
        size = 0;
        //Collection<Node>[] buckets = new Collection[INITIAL_CAPACITY];
        buckets = createTable(INITIAL_CAPACITY);
        maxLoadFactor = LOAD_FACTOR;
        capacity = INITIAL_CAPACITY;
        for (int i = 0; i < capacity; i += 1) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        if (initialSize > 0) {
            capacity = initialSize;
        } else {
            capacity = INITIAL_CAPACITY;
        }
        //Collection<Node>[] buckets = new Collection[capacity];
        buckets = createTable(capacity);
        maxLoadFactor = LOAD_FACTOR;
        for (int i = 0; i < capacity; i += 1) {
            buckets[i] = createBucket();
        }
    }
    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        if (initialSize > 0) {
            capacity = initialSize;
        } else {
            capacity = INITIAL_CAPACITY;
        }
        //Collection<Node>[] buckets = new Collection[capacity];
        buckets = createTable(capacity);
        for (int i = 0; i < capacity; i += 1) {
            buckets[i] = createBucket();
        }
        if (maxLoad > 0 && maxLoad < 1) {
            maxLoadFactor = maxLoad;
        } else {
            maxLoadFactor = LOAD_FACTOR;
        }
    }
    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {

        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {

        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {

        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i += 1) {
            buckets[i] = createBucket();
        }
    }
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        } else if (get(key) != null) {
            return true;
        }
        return false;
    }
    @Override
    public V get(K key) {
        if (key != null) {
            int hashCode = key.hashCode();
            int bucketId = Math.floorMod(hashCode, capacity);
            for (Node node : buckets[bucketId]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void put(K key, V value) {
        Node new_node = createNode(key, value);
        if (containsKey(key) == true) {
            int hashCode = key.hashCode();
            int bucketId = Math.floorMod(hashCode, capacity);
            for (Node node : buckets[bucketId]) {
                if (node.key.equals(key)) {
                    node.value = new_node.value;
                }
            }
        } else {
            size += 1;
            if (size / capacity < maxLoadFactor) {
                int new_hashCode = new_node.key.hashCode();
                int new_bucketId = Math.floorMod(new_hashCode, capacity);
                buckets[new_bucketId].add(new_node);
            } else {
                resize();
                int new_hashCode = new_node.key.hashCode(); // add new element to the resized table
                int new_bucketId = Math.floorMod(new_hashCode, capacity);
                buckets[new_bucketId].add(new_node);
            }
        }
    }
    private void resize() {
        int new_capacity = capacity * 2;
        Collection<Node>[] newTable = createTable(new_capacity);
        for(int i = 0; i < new_capacity; i++){
            newTable[i] = createBucket();
        }
        for (int i = 0; i < capacity; i += 1) {
            for (Node node : buckets[i]) {
                int hashCode = node.key.hashCode(); //move all elements to the resized table
                int new_bucketId = Math.floorMod(hashCode, capacity);
                newTable[new_bucketId].add(node);
            }
        }
        buckets = newTable;
        capacity = new_capacity;
    }
        @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < buckets.length; i += 1) {
            for (Node node : buckets[i]) {
                keyset.add(node.key);
            }
        }
        return keyset;
    }
    @Override
    public V remove(K key) throws UnsupportedOperationException{
        return null;
    }

    @Override
    public V remove(K key, V value) throws UnsupportedOperationException{
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();

  }



}
