import java.util.Random;


class hi_SortComp {
    static void insertionSort(int arr[]) {
        // implementation of Insertion Sort
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            //Move elements of arr[0..i-1], that are  greater than key,
            //to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] a, int n) {
        // implementation of mergeSort
        if (n < 2)
            return;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid); //call with left side
        mergeSort(r, n - mid);//call with right side
        merge(a, l, r, mid, n - mid); // merge both side
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }

        while (i < left){
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    // implementation of quick sort
    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }


    /* The main function that implements QuickSort()
    arr[] --> Array to be sorted,
    low  --> Starting index,
    high  --> Ending index */
    static void quickSort(int arr[], int low, int high) {
        if (low < high)
        {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);
            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    // implementation of heapSort
    static void heapSort(int arr[]){
        int n = arr.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0); // call max heapify on the reduced heap
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]. n is size of heap
    static void heapify(int arr[], int n, int i){
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    // dispaly the array , just used for testing myself if the pogram is correct
    static void printArray(long arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method
    public static void main(String args[]) {
        Random r = new Random();
        int index = 0;
        int d[],c[],b[];
        // the array for storing the values of elapsed time
        // we need to store it becase , it has to be displayed in the table format later.
        long elapsedTimeArrayForInsertionSort[] = new long[10];
        long elapsedTimeArrayForMergeSort[] = new long[10];
        long elapsedTimeArrayForQuickSort[] = new long[10];
        long elapsedTimeArrayForHeapSort[] = new long[10];
        int n = 10000; // initialize with the first length of numbers

        // variables for calculating the elapsed time
        long startTime;
        long endTime;
        long elapsedTime;

        // calculations
        while (n <= 100000) {
            //  create and array of length n to tore the array values
            int arr[] = new int[n];
            // generate random values
            for (int i = 0; i < n; i++) {
                arr[i] = r.nextInt(1000000) + 1;
            }
            //   create three copy for other two algorithms
            b = arr.clone();
            c = arr.clone();
            d = arr.clone();
            // find the elapsed time for insertion sort
            startTime = System.currentTimeMillis();
            insertionSort(arr);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            // store the value in the array elapsedTimeArrayForInsertionSort
            elapsedTimeArrayForInsertionSort[index] = elapsedTime;

            // find the elapsed time for mergeSort sort
            startTime = System.currentTimeMillis();
            mergeSort(b, b.length);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            // store the value in the array elapsedTimeArrayForMergeSort
            elapsedTimeArrayForMergeSort[index] = elapsedTime;

            // find the elapsed time for quickSort sort
            startTime = System.currentTimeMillis();
            quickSort(c, 0,c.length-1);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            // store the value in the array elapsedTimeArrayForQuickSort
            elapsedTimeArrayForQuickSort[index] = elapsedTime;

            // find the elapsed time for quickSort sort
            startTime = System.currentTimeMillis();
            heapSort(d);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            // store the value in the array elapsedTimeArrayForHeapSort
            elapsedTimeArrayForHeapSort[index] = elapsedTime;
            // incerment the index
            index++;
            // incerment the value of the n
            n = n + 10000;
        }


        // System.out.println("Insertion sort Elapsed time : ");
        // printArray(elapsedTimeArrayForInsertionSort);
        // System.out.println("merger sort Elapsed time : ");
        // printArray(elapsedTimeArrayForMergeSort);
        // System.out.println("quick sort Elapsed time : ");
        // printArray(elapsedTimeArrayForQuickSort);
        // System.out.println("heap sort Elapsed time : ");
        // printArray(elapsedTimeArrayForHeapSort);

        // display in the table format
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("\nn\t\t10000 \t20000 \t30000 \t40000 \t50000 \t60000 \t70000 \t80000 \t90000 \t100000");
        System.out.println("Algorithm");
        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.print("insertion\t");
        for(long i : elapsedTimeArrayForInsertionSort){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("merge sort\t");
        for(long i : elapsedTimeArrayForMergeSort){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("quick sort\t");
        for(long i : elapsedTimeArrayForQuickSort){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("heapsort\t");
        for(long i : elapsedTimeArrayForHeapSort){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------");

        // display the linegraph

    } //end of main()
}