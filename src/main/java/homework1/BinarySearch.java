package homework1;

import java.util.ArrayList;

public class BinarySearch {
    public static int[] search(Entry[] entries, String searchableName) {
        int low = 0;
        int high = entries.length - 1;

        Entry temp = new Entry(searchableName, "", "", "", "", "");
        ArrayList<Integer> tempResult = new ArrayList<>();

        while(low<=high){
            int mid = low + (high - low) / 2;
            if(temp.compareTo(entries[mid]) < 0){
                high = mid - 1;
            }
            else if (temp.compareTo(entries[mid]) > 0){
                low = mid + 1;
            }
            else{
                int i = mid;
                while(entries[i].getName().equals(searchableName)){
                    i--;
                }
                while(entries[++i].getName().equals(searchableName)){
                    tempResult.add(i);
                }
                break;
            }
        }

        int[] result = new int[tempResult.size()];

        if(!tempResult.isEmpty()){
            for(int i = 0; i < result.length; i++){
                result[i] = tempResult.get(i);
            }
        }

        return result;
    }
}