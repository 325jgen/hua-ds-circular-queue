import org.hua.project.queue.CircularArray;
import org.hua.project.queue.Queue;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircularArrayTest {

    @Test
    public void testCircularArray() {
        Queue<Integer> q = new CircularArray<>();
        assertTrue(q.isEmpty());
        int count = 100000;
        for (int i = 0; i < count; i++) {
            q.push(i);
            assertTrue(q.size() == i + 1);
            assertTrue(q.first() == 0);
        }
        int current = q.first();
        while (!q.isEmpty()) {
            assertTrue(q.first() == current);
            assertTrue(q.pop() == current);
            current++;
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void testCircularArray2() {
        Queue<Integer> q = new CircularArray<>();
        int count = 12;
        int i;
        for (i = 0; i < count - 1; i++) {
            q.push(i);
            assertTrue(q.size() == i + 1);
        }
        int current = q.first();
        for (i = 0; i < count - 2; i++) {
            assertTrue(q.first() == current++);
            q.pop();
        }
        for (i = q.first() + 1; i < count + 3; i++) {
            q.push(i);
        }
        assertTrue(q.first() == current);
    }

    @Test
    public void testCircularArray3() {
        Queue<Integer> q = new CircularArray<>();
        int count = 10;

        for (int i = 0; i < count; i++) {
            q.push(i);
        }
        assertTrue(q.first() == 0);

        for (int i = 0; i < count - 2; i++) {
            q.pop();
        }
        assertTrue(q.pop() == count - 2);

        for (int i = 0; i < count - 1; i++) {
            q.push(i);
        }
        assertTrue(q.first() == count - 1);
    }
}