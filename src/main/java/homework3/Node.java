package homework3;

import java.util.ArrayList;

public class Node {
    // implement the relevant properties, constructor and methods
    String key;
    ArrayList<Entry> values;
    Node left, right;
    boolean color;

    public Node(String key, Entry entry, boolean color) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(entry);
        this.color = color;
    }
}
