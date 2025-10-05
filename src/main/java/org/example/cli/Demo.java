package org.example.cli;

import org.example.algorithms.MinHeap;
import org.example.metrics.PerformanceTracker;

public class Demo {
    public static void main(String[] args) {
        PerformanceTracker tracker = new PerformanceTracker();

        System.out.println("=== DEMO: MinHeap ===");

        // Создаем кучу
        MinHeap heap = new MinHeap(10, tracker);

        // Вставляем элементы
        heap.insert(10);
        heap.insert(5);
        heap.insert(20);
        heap.insert(3);
        heap.insert(15);

        System.out.println("Heap after insertions:");
        heap.printHeap();

        // Минимум
        System.out.println("Current Min: " + heap.getMin());

        // Уменьшаем ключ (20 -> 2)
        heap.decreaseKey(2, 2);
        System.out.println("Heap after decreaseKey(20 -> 2):");
        heap.printHeap();
        System.out.println("New Min: " + heap.getMin());

        // Извлекаем минимум
        System.out.println("Extracted Min: " + heap.extractMin());
        System.out.println("Heap after extractMin:");
        heap.printHeap();

        // Создаем вторую кучу для merge
        PerformanceTracker tracker2 = new PerformanceTracker();
        MinHeap heap2 = new MinHeap(10, tracker2);
        heap2.insert(7);
        heap2.insert(1);
        heap2.insert(12);

        System.out.println("Second heap:");
        heap2.printHeap();

        // Слияние куч
        heap.merge(heap2);
        System.out.println("Heap after merge:");
        heap.printHeap();

        // Метрики
        System.out.println("Performance metrics: " + tracker);
    }
}
