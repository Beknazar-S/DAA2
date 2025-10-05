package algorithms;

import org.example.metrics.PerformanceTracker;
import org.example.algorithms.MinHeap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void testInsertAndGetMin() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(5);
        heap.insert(3);
        heap.insert(8);

        assertEquals(3, heap.getMin());
    }

    @Test
    void testExtractMin() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(4);
        heap.insert(2);
        heap.insert(7);

        assertEquals(2, heap.extractMin());
        assertEquals(4, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDecreaseKey() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        heap.decreaseKey(2, 5); // decrease 30 -> 5

        assertEquals(5, heap.getMin());
    }
}
