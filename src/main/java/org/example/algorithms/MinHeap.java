package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

import java.util.Arrays;

/**
 * MinHeap implementation with:
 * - insert
 * - extractMin
 * - decreaseKey
 * - merge
 *
 * Time complexity:
 *  - insert: O(log n)
 *  - extractMin: O(log n)
 *  - decreaseKey: O(log n)
 *  - merge: O(n + m) (simple implementation)
 */
public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
        this.tracker = tracker;
    }

    /** Doubles the capacity of the heap array */
    private void ensureCapacity() {
        if (size >= capacity) {
            capacity *= 2;
            heap = Arrays.copyOf(heap, capacity);
            tracker.addMemoryAllocation();
        }
    }

    /** Insert a new key into the heap */
    public void insert(int key) {
        ensureCapacity();
        heap[size] = key;
        tracker.addMemoryAllocation();
        heapifyUp(size);
        size++;
    }

    /** Extract and return the minimum element */
    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    /** Decrease value at index i to newVal */
    public void decreaseKey(int i, int newVal) {
        if (i < 0 || i >= size) throw new IllegalArgumentException("Invalid index");
        if (newVal > heap[i]) throw new IllegalArgumentException("New value is greater than current value");

        heap[i] = newVal;
        heapifyUp(i);
    }

    /** Merge with another heap (simple rebuild) */
    public void merge(MinHeap other) {
        for (int i = 0; i < other.size; i++) {
            insert(other.heap[i]);
        }
    }

    /** Heapify down from index i */
    private void heapifyDown(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size) {
            tracker.addComparison();
            if (heap[left] < heap[smallest]) {
                smallest = left;
            }
        }

        if (right < size) {
            tracker.addComparison();
            if (heap[right] < heap[smallest]) {
                smallest = right;
            }
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    /** Heapify up from index i */
    private void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0) {
            tracker.addComparison();
            if (heap[i] < heap[parent]) {
                swap(i, parent);
                i = parent;
                parent = (i - 1) / 2;
            } else {
                break;
            }
        }
    }

    /** Swap two elements in the heap */
    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        tracker.addSwap();
    }

    /** Get minimum element without removing */
    public int getMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /** Print heap array (for debugging) */
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
