package org.hua.project.queue;

/**
 * A Queue implemented as a Circular Array
 * @param <E>
 */
public class CircularArray<E> implements Queue<E> {

    static private final int DEFAULT_SIZE = 8;
    private E[] array;
    private int size;

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
        if (this.first() == null) {
            throw new IllegalArgumentException("No items left in Array to pop");
        }

        E prev = this.first();
        if (size <= 0.25 * array.length) halfSize();
        f = (f + 1) % array.length;
        size--;
        return prev;
    }

    @Override
    public E first() {
        if (array[f] == null) {
            throw new IllegalArgumentException("Array is empty");
        }
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
        f = 0;
        r = 0;
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
