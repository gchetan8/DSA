import java.util.Arrays;

public class iS {
    static void insertionSort(int[] arr) {

        int n = arr.length;

        for (int i = 1; i < n; i++) {

            int key = arr[i];
            int j = i - 1;

            /*
            {4, 7, 3, 2, 6, 5, 1
            4, 7 | 3
            key - 3
            i = 2
            j = 1

             */
            // Shift elements greater than key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key at its correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {

        int[] arr = {4, 7, 3, 2, 6, 5, 1};

        insertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
