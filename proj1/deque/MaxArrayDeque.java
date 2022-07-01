package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> compare;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        compare = c;
    }
    public T max() {
        if (super.isEmpty()) {
            return null;
        }
        int size = super.size();
        T max = super.get(0);
        for (int i = 1; i < size; i++) {
            if (compare.compare(max, super.get(i)) < 0) {
                max = super.get(i);
            }
        }
        return max;
    }
    public T max(Comparator<T> c) {
        compare = c;
        return max();
    }

}
