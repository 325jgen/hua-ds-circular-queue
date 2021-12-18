import org.junit.Test;

import static org.junit.Assert.*;

public class CircularArrayTest {

    @Test
    public void testLinkedQueue() {
        Queue<Integer> q = new CircularArray<>();
        assertTrue(q.isEmpty());
        int count = 100000;
        for (int i = 0; i < count; i++) {
            q.push(i);
            assertTrue(q.size() == i + 1);
            assertTrue(q.first() == 0);
        }
        int current = 0;
        while (!q.isEmpty()) {
            assertTrue(q.first() == current);
            assertTrue(q.pop() == current);
            current++;
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void testLinkedQueue2() {

    }

    @Test
    public void testLinkedQueue3() {

    }

    @Test
    public void testLinkedQueue4() {

    }

}
