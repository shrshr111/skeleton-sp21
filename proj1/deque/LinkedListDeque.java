package deque;

public class LinkedListDeque<T> {
    public class IntNode {

        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {

        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        IntNode head = new IntNode(sentinel, item, sentinel.next);
        sentinel.next = head;
        head.next.prev = head;
        size += 1;
    }

    public void addLast(T item) {
        size = size + 1;
        IntNode p = sentinel;
        IntNode tail = new IntNode(p.prev, item, p);
        p.prev.next = tail;
        p.prev = tail;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel;
        for (int j = 0; j < size; j++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if(size > 0) {
            IntNode p = sentinel;
            IntNode head = p.next;
            p.next = head.next;
            head.next.prev = p;
            size -= 1;
            return head.item;
        }
        return null;
    }
    public T removeLast() {
        if(size > 0) {
            IntNode p = sentinel;
            IntNode tail = p.prev;
            p.prev = tail.prev;
            tail.prev.next = p;
            size -= 1;
            return tail.item;
        }
        return null;
    }
    public T get(int index) {
        if(index >= size){
            return null;
        }
        IntNode p = sentinel;
        for (int j = 0; j <= index; j++) {
            p = p.next;
        }
        return p.item;
    }
    public T getRecursive(int index) {
        IntNode p = sentinel;
        if (index < 0 || index >= size) {
            return null;
        }
        return recursive(p, index).item;
    }
    private IntNode recursive(IntNode p, int index) {
        if (index == 0) {
            return p.next;
        }
            return recursive(p.next, index - 1);
    }

    //public Iterator<T> iterator() {

    //}
    //public boolean equals(Object o) {

    //}

}
