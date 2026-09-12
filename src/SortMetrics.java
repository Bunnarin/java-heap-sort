package src;

/**
 * Holds performance statistics collected while a sorting algorithm runs:
 * number of comparisons, number of swaps/movements, and runtime in nanoseconds.
 */
public class SortMetrics {

    public String algorithmName;
    public long comparisons = 0;
    public long swaps = 0;
    public long runtimeNanos = 0;

    public SortMetrics(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    public String toString() {
        return String.format(
                "%-16s | Comparisons: %,13d | Swaps/Movements: %,13d | Runtime: %,12.3f ms",
                algorithmName, comparisons, swaps, runtimeNanos / 1_000_000.0);
    }
}