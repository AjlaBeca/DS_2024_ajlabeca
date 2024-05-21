package homework1;

import java.io.IOException;
import java.util.Scanner;

public class PhonebookV1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = "raw_phonebook_data.csv";
        String outputFilePath = "sorted_phonebook_data.csv";

        System.out.println("Loading entries from file...");
        Entry[] entries = new Entry[0];
        try {
            entries = FileUtils.readFile(inputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Sorting entries...");
        MergeSort.sort(entries);

        System.out.println("Saving sorted entries to file...");
        try {
            FileUtils.writeToFile(entries, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {
            System.out.print("Enter a name to search (or -1 to exit): ");
            String searchableName = scanner.nextLine();
            if (searchableName.equals("-1")) {
                break;
            }

            int[] searchResult = BinarySearch.search(entries, searchableName);
            if (searchResult.length == 0) {
                System.out.println("No entries found.");
            } else {
                System.out.printf("%d entries found:\n", searchResult.length);
                for (int index : searchResult) {
                    System.out.println(entries[index]);
                }
            }
        }


        scanner.close();
    }
}
