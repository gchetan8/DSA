import java.util.Arrays;

public class selectionSort {
    static void selectionsort(int[] arr){
        int r = arr.length;
        for (int i = 0; i < r; i++) {
            int minI = i;
            for (int j = i + 1; j < r ; j++) {
                if(arr[j] < arr[minI])
                    minI = j;
            }
            swap(arr, minI, i);
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int arr[] = {4, 7, 3, 6, 5, 1, 2};
        selectionsort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
