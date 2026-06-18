import java.util.Arrays;

public class QuickSort1 {

    static void quicksort(int[] arr, int l, int r) {
        if (l < r) {
            // Partition function will put the pivot in right place & return the right partition index
            int partitionIndex = partitionIndex(arr,l,r);
            quicksort(arr, l, partitionIndex -1);
            quicksort(arr, partitionIndex +1, r);
        }
    }

    static int partitionIndex(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l+1;
        int j = r;


        while(i <= r-1 && j >= l+1){
            if( arr[i] <= pivot)
                i++;
            if( arr[j] > pivot)
                j--;
            if (i < j)
                swap(arr, i,j);
        }

        swap(arr,l,j);
        return j;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {4, 2, 3, 6, 1 , 5};
        quicksort(arr, 0, arr.length -1);
        System.out.println(Arrays.toString(arr));
    }
}
