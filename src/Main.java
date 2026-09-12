package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    // Fixed seeds so every run is reproducible and every algorithm sees the
    // exact same input for a given dataset size.
    private static final long BASE_SEED = 42L;

    public static void main(String[] args) throws IOException {
        boolean runQuadraticOnLarge = args.length > 0 && args[0].equalsIgnoreCase("full");

        int[] sizes = { 5, 1_000, 1_000_000 };

        File outDir = new File("output");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        StringBuilder report = new StringBuilder();

        for (int s = 0; s < sizes.length; s++) {
            int size = sizes[s];

            report.append("---- Dataset: ").append(" (n = ").append(size).append(") ----\n");

            int[] original = generateRandomArray(size, BASE_SEED + s);

            List<SortMetrics> results = new ArrayList<>();

            // Heap Sort always runs on every dataset size.
            results.add(runAndSave("heap", original));

            boolean runQuadratic = size != 1_000_000 || runQuadraticOnLarge;
            if (runQuadratic) {
                results.add(runAndSave("bubble", original));
                results.add(runAndSave("selection", original));
                results.add(runAndSave("insertion", original));
            } else {
                String note = "NOTE: Bubble, Selection, and Insertion Sort were skipped for n = 1000000 because they would take too long to finish. Re-run with the argument 'full' to include it\n";
                report.append(note).append("\n");
            }

            for (SortMetrics m : results) {
                report.append(m).append("\n");
            }
            report.append("\n");
        }

        // Save the full performance report to a text file as well.
        try (PrintWriter pw = new PrintWriter(new FileWriter("output/performance_report.txt"))) {
            pw.print(report);
        }

        System.out.println("\nAll sorted output files were saved to the 'output' directory.");
        System.out.println("Full performance report saved to output/performance_report.txt");
    }

    private static SortMetrics runAndSave(String name, int[] original)
            throws IOException {
        int[] arr = Arrays.copyOf(original, original.length);

        SortMetrics m;
        switch (name) {
            case "heap":
                m = SortAlgorithms.heapSort(arr);
                break;
            case "bubble":
                m = SortAlgorithms.bubbleSort(arr);
                break;
            case "selection":
                m = SortAlgorithms.selectionSort(arr);
                break;
            case "insertion":
                m = SortAlgorithms.insertionSort(arr);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }

        String filename = "output/" + name + "_sort_" + "n=" + original.length + "_output.txt";
        saveArrayToFile(arr, filename);

        return m;
    }

    private static int[] generateRandomArray(int size, long seed) {
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10_000_000);
        }
        return arr;
    }

    private static void saveArrayToFile(int[] arr, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename), 1 << 16)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append('\n');
                if (sb.length() > 1_000_000) {
                    bw.write(sb.toString());
                    sb.setLength(0);
                }
            }
            bw.write(sb.toString());
        }
    }
}