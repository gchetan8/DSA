import java.util.Arrays;


public class quickSort {

    static void qSort(int[] arr, int l, int h) {
      if(l < h) {
          int partition = partitionIndex(arr, l, h);
          qSort(arr, l, partition -1);
          qSort(arr, partition+1, h);
      }
    }

    static int partitionIndex(int[] arr, int l, int h) {
        int pivot = arr[l];
        int i = l;
        int j = h;

        while (i < j) {
            while( i <= h-1 && (arr[i] <= pivot)) {
                i++;
            }
            while(j >=l+1 && (arr[j] > pivot)) {
                j--;
            }
            if( i < j)
                swap(arr, i, j);
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
        int[] arr = {4, 7, 3, 2, 6, 5, 1};
        qSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
