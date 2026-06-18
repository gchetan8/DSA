import java.util.Arrays;

public class bubbleSort {

    static void bubblesort(int[] arr){
        int r = arr.length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r - i - 1; j++) {
                if(arr[j] > arr[j+1])
                    swap(arr, j, j+1);
            }
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int arr[] = {4, 7, 3, 6, 5, 1, 2};
        bubblesort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
