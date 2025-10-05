package org.example.cli;

import org.example.algorithms.MinHeap;
import org.example.metrics.PerformanceTracker;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] inputSizes = {1000, 5000, 10000, 20000, 50000};
        List<Double> times = new ArrayList<>();

        System.out.println("=== MinHeap Benchmark ===");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "n", "Time (ms)", "Comparisons", "Swaps");

        // Убедимся, что папка docs существует
        File docsDir = new File("docs");
        if (!docsDir.exists()) {
            docsDir.mkdirs();
        }

        // CSV для записи результатов
        File csvFile = new File("docs/performance-data.csv");

        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("n,Time(ms),Comparisons,Swaps\n");

            for (int n : inputSizes) {
                PerformanceTracker tracker = new PerformanceTracker();
                MinHeap heap = new MinHeap(n, tracker);
                Random rand = new Random();

                long start = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    heap.insert(rand.nextInt(100000));
                }
                long end = System.nanoTime();

                double timeMs = (end - start) / 1_000_000.0;
                times.add(timeMs);

                System.out.printf("%-10d %-15.3f %-15d %-15d%n",
                        n, timeMs, tracker.getComparisons(), tracker.getSwaps());

                writer.write(n + "," + timeMs + "," + tracker.getComparisons() + "," + tracker.getSwaps() + "\n");
            }

            System.out.println("\n✅ Данные сохранены в: docs/performance-data.csv");

        } catch (IOException e) {
            System.err.println("❌ Ошибка записи CSV: " + e.getMessage());
        }

        // === Построение графика ===
        List<Integer> xData = new ArrayList<>();
        for (int n : inputSizes) {
            xData.add(n);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("MinHeap Performance")
                .xAxisTitle("Input Size (n)")
                .yAxisTitle("Execution Time (ms)")
                .build();

        XYSeries series = chart.addSeries("Execution Time", xData, times);
        series.setMarker(SeriesMarkers.CIRCLE);

        try {
            BitmapEncoder.saveBitmap(chart, "docs/performance-plot", BitmapEncoder.BitmapFormat.PNG);
            System.out.println("✅ График сохранён: docs/performance-plot.png");
        } catch (IOException e) {
            System.err.println("❌ Ошибка при сохранении графика: " + e.getMessage());
        }

        // Показать график в окне
        new SwingWrapper<>(chart).displayChart();
    }
}
