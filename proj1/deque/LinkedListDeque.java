package deque;
import java.util.Iterator;
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class IntNode {
        IntNode prev;
        T item;
        IntNode next;

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

    //public LinkedListDeque(T x) {
      //  sentinel = new IntNode(null, null, null);
        //sentinel.next = new IntNode(sentinel, x, sentinel);
        //sentinel.prev = sentinel.next;
        //size = 1;
    //}

    @Override
    public void addFirst(T item) {
        IntNode head = new IntNode(sentinel, item, sentinel.next);
        sentinel.next = head;
        head.next.prev = head;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        size = size + 1;
        IntNode p = sentinel;
        IntNode tail = new IntNode(p.prev, item, p);
        p.prev.next = tail;
        p.prev = tail;
    }
//@Override
  //  public boolean isEmpty() {
    //    return size == 0;
    //}
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        IntNode p = sentinel;
        for (int j = 0; j < size; j++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (size > 0) {
            IntNode p = sentinel;
            IntNode head = p.next;
            p.next = head.next;
            head.next.prev = p;
            size -= 1;
            return head.item;
        }
        return null;
    }
    @Override
    public T removeLast() {
        if (size > 0) {
            IntNode p = sentinel;
            IntNode tail = p.prev;
            p.prev = tail.prev;
            tail.prev.next = p;
            size -= 1;
            return tail.item;
        }
        return null;
    }
    @Override
    public T get(int index) {
        if (index >= size) {
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

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        LinkedListDequeIterator() {
            wizPos = 0;
        }
        @Override
        public boolean hasNext() {
            return wizPos < size;
        }
        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }
}