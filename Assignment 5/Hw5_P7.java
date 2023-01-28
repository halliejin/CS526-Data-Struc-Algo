/**
 * CS526
 */

import java.util.Random;

public class Hw5_P7 {
    public static void main(String[] args) {
        long[] timeIS = new long[10];
        long[] timeMS = new long[10];
        long[] timeQS = new long[10];
        long[] timeHS = new long[10];

        int cnt = 0;

        Random r = new Random();

        for (int n = 10000; n <= 100000; n = n + 10000){
            int[] arr1 = new int[n];
            for (int i = 0; i < n; i++){
                arr1[i] = r.nextInt(1000000) + 1;
            }
            int[] arr2 = arr1.clone();
            int[] arr3 = arr1.clone();
            int[] arr4 = arr1.clone();

            // elapsed time for insertion sort
            long startTime = System.currentTimeMillis();
            insertionsort(arr1);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            timeIS[cnt] = elapsedTime;

            // elapsed time for merge sort
            startTime = System.currentTimeMillis();
            mergesort(arr2, 0, arr2.length - 1);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            timeMS[cnt] = elapsedTime;

            // elapsed time for quick sort
            startTime = System.currentTimeMillis();
            quicksort(arr3, 0, arr3.length - 1);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            timeQS[cnt] = elapsedTime;

            // elapsed time for heap sort
            startTime = System.currentTimeMillis();
            heapsort(arr4);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            timeHS[cnt] = elapsedTime;

            cnt++;
        }

        System.out.println("insertionsort: " );
        for (long i : timeIS) System.out.print(i + "  ");
        System.out.println();
        System.out.println("mergesort: " );
        for (long i : timeMS) System.out.print(i + "  ");
        System.out.println();
        System.out.println("quicksort: " );
        for (long i : timeQS) System.out.print(i + "  ");
        System.out.println();
        System.out.println("heapsort: ");
        for (long i : timeHS) System.out.print(i + "  ");
        System.out.println();

    }


    // insertion sort
    public static void insertionsort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int insertValue = arr[i];
            int insertIndex = i;
            while (insertIndex > 0 && insertValue < arr[insertIndex - 1]){
                arr[insertIndex] = arr[insertIndex - 1];
                insertIndex--;
            }
            arr[insertIndex] = insertValue;
        }
    }

    // merge sort (ref: textbook modified)
    public static void mergesort(int[]arr, int left, int right){
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergesort(arr, left, mid);
        mergesort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
    public static void merge(int[]arr, int left, int mid, int right){
        int s1 = left, s2 = mid + 1;
        int[] temp = new int[right - left + 1];
        int i = 0;

        while (s1 <= mid && s2 <= right){
            if (arr[s1] > arr[s2]) temp[i++] = arr[s2++];
            else temp[i++] = arr[s1++];
        }
        while (s1 <= mid){
            temp[i++] = arr[s1++];
        }
        while (s2 <= right){
            temp[i++] = arr[s2++];
        }
        for (int j = 0; j < temp.length; j++){
            arr[left + j] = temp[j];
        }
    }

    // quick sort (ref: https://www.geeksforgeeks.org/quick-sort/)
    static void quicksort(int arr[], int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);
            // Recursively sort elements before partition and after partition
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }
    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // heap sort (ref: https://www.geeksforgeeks.org/heap-sort/)
    public static void heapsort(int[] arr){
        int N = arr.length;
        for (int i = N / 2 - 1; i >= 0; i--) heapify(arr, N, i);

        for(int i = N - 1; i > 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }
    public static void heapify(int[] arr, int N, int i){
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

}
