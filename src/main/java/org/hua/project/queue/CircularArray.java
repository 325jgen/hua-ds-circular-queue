package org.hua.project.queue;

import java.util.NoSuchElementException;

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
            throw new NoSuchElementException("No items left in Array to pop");
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
            throw new NoSuchElementException("Array is empty");
        }
        return array[f];
    }

    @Override
    public boolean isEmpty() {
        return f == r && size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        this.array = (E[]) new Object[DEFAULT_SIZE];
        this.f = 0;
        this.r = 0;
        this.size = 0;
    }

    private void doubleSize() {
        int newCapacity = 2 * array.length;
        E[] newArray = (E[]) new Object[newCapacity];

        int index = 0;
        for (int i = f; i < array.length; i++) {
            index = i - f;
            newArray[index] = array[i];
        }
        index++;
        if (r <= f) {
            for (int i = 0; i < r-1; i++) {
                newArray[index++] = array[i];
            }
        }

        this.array = newArray;
        r = size = index;
        f = 0;
    }

    private void halfSize() {
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
