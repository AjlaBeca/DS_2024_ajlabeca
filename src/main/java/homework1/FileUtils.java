package homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Entry[] readFile(String filePath) throws FileNotFoundException {
        List<Entry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Entry entry = new Entry(data[0], data[1], data[2], data[3], data[4], data[5]);
                entries.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries.toArray(new Entry[0]);
    }

    public static void writeToFile(Entry[] entries, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("name;street_address;city;postcode;country;phone_number\n");
            for (Entry entry : entries) {
                String line = entry.getName() + ";"
                        + entry.getStreetAddress() + ";"
                        + entry.getCity() + ";"
                        + entry.getPostcode() + ";"
                        + entry.getCountry() + ";"
                        + entry.getPhoneNumber() +"\n";
                writer.write(line);
            }
        }
    }
}
