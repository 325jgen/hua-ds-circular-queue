public class CircularArray<E> implements Queue<E> {

    private E[] array;
    private int size;
    // f > r --> wrapped array (n - (f - r) + 1)
    // f < r --> serial array (r - f - 1)
    // f = r --> empty array
    private int f, r, n;

    @Override
    public void push(E elem) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E first() {
        return array[f % size];
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public int size() {
        return f > r ? n - (f-r) + 1 : r - f - 1;
    }

    @Override
    public void clear() {

    }
}
