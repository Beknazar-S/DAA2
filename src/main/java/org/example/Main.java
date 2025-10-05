package org.example;

import org.example.algorithms.MinHeap;
import org.example.metrics.PerformanceTracker;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== MinHeap Demo ===");

        // создаём объект для замера операций
        PerformanceTracker tracker = new PerformanceTracker();

        // создаём кучу на 10 элементов с трекером
        MinHeap heap = new MinHeap(10, tracker);

        // Вставляем элементы
        heap.insert(15);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);
        heap.insert(25);

        System.out.println("После вставки элементов:");
        heap.printHeap();

        // Уменьшаем ключ (decreaseKey)
        heap.decreaseKey(2, 3);
        System.out.println("После decreaseKey (index=2, newVal=3):");
        heap.printHeap();

        // Извлекаем минимум
        int min = heap.extractMin();
        System.out.println("Извлечён минимальный элемент: " + min);
        System.out.println("После extractMin:");
        heap.printHeap();

        // Получаем минимум без удаления
        System.out.println("Текущий минимум: " + heap.getMin());

        // Печатаем статистику трекера
        System.out.println("\n=== Метрики ===");
        System.out.println("Сравнения: " + tracker.getComparisons());
        System.out.println("Перестановки: " + tracker.getSwaps());
        System.out.println("Доступы к массиву: " + tracker.getArrayAccesses());
    }
}

