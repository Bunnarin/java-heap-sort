package src;

public class SortAlgorithms {
    public static SortMetrics heapSort(int[] arr) {
        SortMetrics m = new SortMetrics("Heap Sort");
        long start = System.nanoTime();

        int n = arr.length;

        // Build a max-heap from the array
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, m);
        }

        for (int end = n - 1; end > 0; end--) {
            // move the current max (root) to the end and re-heapify
            int tmp = arr[0];
            arr[0] = arr[end];
            arr[end] = tmp;
            m.swaps++;

            heapify(arr, end, 0, m);
        }

        m.runtimeNanos = System.nanoTime() - start;
        return m;
    }

    private static void heapify(int[] arr, int heapSize, int i, SortMetrics m) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize) {
            m.comparisons++;
            if (arr[left] > arr[largest]) {
                largest = left;
            }
        }

        if (right < heapSize) {
            m.comparisons++;
            if (arr[right] > arr[largest]) {
                largest = right;
            }
        }

        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            m.swaps++;

            heapify(arr, heapSize, largest, m);
        }
    }

    public static SortMetrics bubbleSort(int[] arr) {
        SortMetrics m = new SortMetrics("Bubble Sort");
        long start = System.nanoTime();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swappedThisPass = false;
            for (int j = 0; j < n - i - 1; j++) {
                m.comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    m.swaps++;
                    swappedThisPass = true;
                }
            }
            if (!swappedThisPass) {
                break; // already sorted, early exit
            }
        }

        m.runtimeNanos = System.nanoTime() - start;
        return m;
    }

    public static SortMetrics selectionSort(int[] arr) {
        SortMetrics m = new SortMetrics("Selection Sort");
        long start = System.nanoTime();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
                m.swaps++;
            }
        }

        m.runtimeNanos = System.nanoTime() - start;
        return m;
    }

    public static SortMetrics insertionSort(int[] arr) {
        SortMetrics m = new SortMetrics("Insertion Sort");
        long start = System.nanoTime();

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // shift element right ("movement")
                    m.swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }

        m.runtimeNanos = System.nanoTime() - start;
        return m;
    }
}