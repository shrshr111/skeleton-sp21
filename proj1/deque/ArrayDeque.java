package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int head;
    private int tail;




    public ArrayDeque() {

        items = (T[]) new Object[32];
        size = 0;
        head = 0;
        tail = 0;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        tail = size - 1;
        head = 0;
        items = a;
    }
    @Override
    public void addFirst(T item) {
        if (size > 0) {
            if (size == items.length) {
                resize(size * 2);
            }
            if (head == 0) {
                head = head + items.length - 1;
            } else {
                head = head - 1;
            }
        }
        items[head] = item;
        size = size + 1;
    }
    @Override
    public void addLast(T item) {
        if (size > 0) {
            if (size == items.length) {
                resize(size * 2);
            }
            if (tail == items.length - 1) {
                tail = tail - items.length + 1;
            } else {
                tail = tail + 1;
            }
        }
        items[tail] = item;
        size = size + 1;
    }

    //public boolean isEmpty() {
      //  if (size == 0) {
//            return true;
  //      }
    //    return false;
   // }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (head < tail) {
            for (int i = head; i <= tail; i++) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = head; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i <= tail; i++) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (size > 0) {
            T item = items[head];
            if (size > 1) {
                head = head + 1;
                if (head >= items.length) {
                    head = head - items.length;
                }
            }
            size = size - 1;
            if (size >= 1 && items.length > 4 * size) {
                resize(items.length / 4);
            }
            return item;
        }
        return null;
    }
    @Override
    public T removeLast() {
        if (size > 0) {
            T item = items[tail];
            if (size > 1) {
                tail = tail - 1;
                if (tail < 0) {
                    tail = tail + items.length;
                }
            }
            size = size - 1;
            if (size >= 1 && items.length > 4 * size) {
                resize(items.length / 4);
            }
            return item;
        }
        return null;
    }
    @Override
    public T get(int index) {
        if (head <= tail) {
            if (index + head > tail) {
                return null;
            }
            return items[index + head];
        } else {
            int newindex = index + head;
            if (newindex >= items.length) {
                newindex = newindex - items.length;
                if (newindex > tail) {
                    return null;
                }
            }
            return items[newindex];
        }
    }
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int wizPos;

        private ArrayIterator() {
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
        if (this == o) {
            return true;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
