import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mergesort {

    static void mergeSort(int[] arr, int l, int r) {
        if(l == r) {
            return;
        }
            int mid = (r+l)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr,l,mid,r);

    }

    static void merge(int[] arr, int l, int mid, int r) {
        int i = l;
        int j = mid+1;
        List<Integer> temp = new ArrayList<>();
        while(i <= mid && j <= r) {
            if(arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }
        while(i <= mid) {
            temp.add(arr[i]);
            i++;
        }
        while(j <= r){
            temp.add(arr[j]);
            j++;
        }

        for (int k = l; k <= r; k++) {
            arr[k] = temp.get(k - l);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 5, 3, 6, 1, 2};
        mergeSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
