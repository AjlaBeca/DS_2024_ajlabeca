package homework1;

import java.util.ArrayList;

public class BinarySearch {
    public static int[] search(Entry[] entries, String searchableName) {
        int low = 0;
        int high = entries.length - 1;

        Entry temp = new Entry(searchableName, "", "", "", "", "");
        ArrayList<Integer> tempResult = new ArrayList<>();

        while(low <= high){
            int mid = low + (high - low) / 2;
            int comparison = temp.compareTo(entries[mid]);

            if (comparison < 0) {
                high = mid - 1;
            } else if (comparison > 0) {
                low = mid + 1;
            } else {

                int start = mid;
                while (start >= 0 && entries[start].getName().equals(searchableName)) {
                    start--;
                }
                start++;


                int end = mid;
                while (end < entries.length && entries[end].getName().equals(searchableName)) {
                    end++;
                }
                end--;

                for (int i = start; i <= end; i++) {
                    tempResult.add(i);
                }
                break;
            }
        }

        int[] result = new int[tempResult.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = tempResult.get(i);
        }

        return result;
    }
}
