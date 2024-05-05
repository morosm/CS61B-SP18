package synthesizer;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayRingBuffer
{
    @Test
    public void testEnque(){
        BoundedQueue<Double> x = new ArrayRingBuffer<>(4);
        assertTrue(x.capacity() == 4);
        assertTrue(x.isEmpty());
        assertFalse(x.isFull());
        assertTrue(x.fillCount() == 0);
        assertTrue(x.peek() == null);
        //assertThrow需要4.13才能用

        x.enqueue(33.1); // 33.1 null null  null
        assertTrue(x.fillCount() == 1);
        assertTrue(x.peek() == 33.1);

        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(62.3); // 33.1 44.8 62.3  null
        x.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        assertTrue(x.fillCount() == 4);
        assertTrue(x.isFull());
        assertFalse(x.isEmpty());

        assertTrue(x.dequeue() == 33.1);
        x.dequeue();
        x.dequeue();
        x.dequeue();
        assertTrue(x.isEmpty());
        assertFalse(x.isFull());
    }
}
