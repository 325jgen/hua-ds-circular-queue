public class CircularArray<E> implements Queue<E> {

    static private final int DEFAULT_SIZE = 8;
    private E[] array;
    private int size;

    // f > r --> wrapped array (n - (f - r) + 1)
    // f < r --> serial array (r - f - 1)
    // f = r --> empty array
    private int f, r;

    @SuppressWarnings("unchecked")
    public CircularArray() {
        this.array = (E[]) new Object[DEFAULT_SIZE];
        this.size = 0;
        this.f = 0;
        this.r = 0;
    }

    @Override
    public void push(E elem) {
        if (f == r && size != 0) doubleSize();
        array[r] = elem;
        r = (r + 1) % array.length;
        size++;
    }

    @Override
    public E pop() {
        E prev = array[f];
        if (size <= 0.25 * array.length) halfSize();
        f = (f + 1) % array.length;
        size--;
        return prev;
    }

    @Override
    public E first() {
        return array[f];
    }

    @Override
    public boolean isEmpty() {
        return f == r && size == 0;
    }

    @Override
    public int size() {
        if (f > r) return size - (f - r);
        if (f < r) return r - f;
        if (size == 0) return 0;
        return size;
    }

    @Override
    public void clear() {
        array = null;
        size = 0;
    }

    public void doubleSize() {
        int newCapacity = 2 * array.length;
        E[] newArray = (E[]) new Object[newCapacity];

        if (size >= 0) System.arraycopy(array, f, newArray, r, size);

        this.array = newArray;
        r = size;
    }

    public void halfSize() {
        if (array.length <= DEFAULT_SIZE) {
            return;
        }

        int newCapacity = array.length / 2;
        E[] newArray = (E[]) new Object[newCapacity];

        if (r - f >= 0) System.arraycopy(array, f, newArray, 0, Math.min((r - f), size));

        this.array = newArray;
        this.size = r = Math.min(size, newCapacity);
        f = 0;
    }
}
