package homework3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileUtils {
    public static RedBlackTree readFile(String filePath) throws FileNotFoundException {
        RedBlackTree tree = new RedBlackTree();
        Scanner scanner = new Scanner(new FileReader(filePath));
        boolean isFirstLine = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            if (line.isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 6) {
                System.out.println("Invalid line skipped: " + line);
                continue;
            }

            // Directly use the name field without splitting
            String name = data[0];

            Entry entry = new Entry(name, data[1], data[2], data[3], data[4], data[5]);
            tree.put(name, entry);
        }
        scanner.close();
        return tree;
    }
}
