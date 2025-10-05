package org.example.metrics;

public class PerformanceTracker {
    private int comparisons;
    private int swaps;
    private int arrayAccesses;
    private int memoryAllocations;

    public void addComparison() {
        comparisons++;
    }

    public void addSwap() {
        swaps++;
    }

    public void addArrayAccess() {
        arrayAccesses++;
    }

    public void addMemoryAllocation() {
        memoryAllocations++;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }

    public int getMemoryAllocations() {
        return memoryAllocations;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
    }
}
