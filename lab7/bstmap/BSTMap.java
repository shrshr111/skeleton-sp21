package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        size = 0;
        root = null;
    }
    @Override
    public void clear() {
        size = 0;
        root = null;
    }
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        if (find(key, root) != null) {
            return true;
        } else {
            return false;
        }
    }

    private Node find(K key, Node x){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return find(key, x.left);
        } else if (cmp > 0) {
            return find(key, x.right);
        } else {
            return x;
        }
    }
    @Override
    public V get(K key) {
        return get(key, root);
    }
    private V get(K key, Node x)  {
        if (key == null) {
            return null;
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(key, x.left);
        } else if (cmp > 0) {
            return get(key, x.right);
        } else {
            return x.value;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key!");
        } else {
            root = put(key, value, root);
        }
    }
    private Node put(K key, V value, Node x) {
        if (x == null) {
           size += 1;
           return new Node(key,value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(key,value,x.left);
            return x;
        }
        else if (cmp > 0) {
            x.right = put(key,value,x.right);
            return x;
        } else {
            x.value = value;
            return x;
        }
    }

    public void printInOrder() {
        List<K> res = new ArrayList<>();
        inOrderTraversal(root, res);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private void inOrderTraversal(Node x, List<K> res){
        if(x != null){
            inOrderTraversal(x.left, res);
            res.add(x.key);
            inOrderTraversal(x.right, res);
        }
    }

    @Override
    public Set<K> keySet() throws UnsupportedOperationException{
        return null;
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
    public Iterator<K> iterator() throws UnsupportedOperationException {
        return null;
    }
}
