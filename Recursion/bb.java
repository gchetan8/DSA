import java.util.Arrays;

public class bb {

    static void bubblesort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n-i-1; j++) {
                if(arr[j] > arr[j+1])
                    swap(arr, j, j+1);
            }
        }
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
         int[] arr = {4, 3, 6, 5, 2, 1, 7};
         bubblesort(arr);
         System.out.println(Arrays.toString(arr));
    }
}
