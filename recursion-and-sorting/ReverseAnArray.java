import java.util.Arrays;

public class ReverseAnArray {

    static void reverse(int[] arr, int left, int right) {
        if (left >= right) return;
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
        reverse(arr, left+1, right - 1);
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        reverse(arr, 0, arr.length - 1);
//        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr));
//        }
    }
}
