package deque;

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque(){

        items = (T[]) new Object[1000];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(T item) {
        if (size > 0) {
            if (size == items.length) {
                resize(size * 1000);
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
    public void addlast(T item) {
        if (size > 0) {
            if (size == items.length) {
                resize(size * 1000);
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
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public int size() {
        return size;
    }
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
            return item;
        }
        return null;
    }
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
            return item;
        }
        return null;
    }
    public T get(int index) {
        if (head <= tail) {
            if(index + head > tail){
                return null;
            }
            return items[index + head];
        } else {
            int new_index = index + head;
            if(new_index >= items.length){
                new_index = new_index - items.length;
            }
            if(new_index > tail){
                return null;
            }
            return items[new_index];
        }
    }
    public Iterator<T> iterator() {

    }
    public boolean equals(Object o) {

    }
}
