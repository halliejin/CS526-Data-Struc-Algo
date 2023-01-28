import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Hw4_p6 {
    public static void main(String[] args) {
        // create a HashMap instance myMap
        HashMap<Integer, Integer> myMap = new HashMap<>();
        // create an ArrayList instance myArrayList
        ArrayList<Integer> myArrayList = new ArrayList<>();
        // create a LinkedList instance myLinkedList
        LinkedList<Integer> myLinkedList = new LinkedList<>();

        int[] insertKeys = new int[100000];
        int[] searchKeys = new int[100000];
        long totalHashMapIT = 0, totalArrayListIT = 0, totalLinkedListIT = 0;
        long totalHashMapST = 0, totalArrayListST = 0, totalLinkedListST = 0;

        for (int j = 0; j < 10; j++) {
            Random r = new Random(System.currentTimeMillis());
            // create a new array named insertKeys and store integers

            for (int i = 0; i < insertKeys.length; i++) {
                insertKeys[i] = r.nextInt(1000000) + 1;
            }

            //HashMap insertion time calculate
            // record the start time of the insertion process of HashMap
            long startTime = System.currentTimeMillis();
            // put integers into the map
            for (int i = 0; i < insertKeys.length; i++) {
                myMap.put(insertKeys[i], i);
            }
            // record the end time of the insertion process of HashMap
            long endTime = System.currentTimeMillis();
            // calculate the elapsed time
            long elapsedTime = endTime - startTime;
            totalHashMapIT += elapsedTime;


            //ArrayList insertion time calculate
            startTime = System.currentTimeMillis();
            for (int i = 0; i < insertKeys.length; i++) {
                myArrayList.add(insertKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalArrayListIT += elapsedTime;

            // LinkedList insertion time calculate
            startTime = System.currentTimeMillis();
            for (int i = 0; i < insertKeys.length; i++) {
                myLinkedList.add(insertKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalLinkedListIT += elapsedTime;


            r.setSeed(System.currentTimeMillis());

            for (int i = 0; i < 100000; i++) {
                searchKeys[i] = r.nextInt(2000000) + 1;
            }

            // HashMap search time
            startTime = System.currentTimeMillis();
            for (int i = 0; i < searchKeys.length; i++) {
                myMap.containsKey(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalHashMapST += elapsedTime;

            // ArrayList search time
            startTime = System.currentTimeMillis();
            for (int i = 0; i < searchKeys.length; i++) {
                myArrayList.contains(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalArrayListST += elapsedTime;

            // LinkedList search time
            startTime = System.currentTimeMillis();
            for (int i = 0; i < searchKeys.length; i++) {
                myLinkedList.contains(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            totalLinkedListST+= elapsedTime;

            myMap.clear();
            myArrayList.clear();
            myLinkedList.clear();
            j++;
        }

        System.out.println("Number of keys = " + insertKeys.length);
        System.out.println();
        System.out.println("HashMap average total insert time = " + totalHashMapIT/10);
        System.out.println("ArrayList average total insert time = " + totalArrayListIT/10);
        System.out.println("LinkedList average total insert time = " + totalLinkedListIT/10);
        System.out.println("HashMap average total search time = " + totalHashMapST/10);
        System.out.println("ArrayList average total search time = " + totalArrayListST/10);
        System.out.println("LinkedList average total search time = " + totalLinkedListST/10);

    }
}
