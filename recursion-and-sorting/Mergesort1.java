import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mergesort1 {

    static void mergesort(int[] arr, int l, int r) {
        if (l==r)
            return;
        int mid = (l+r)/2;

        mergesort(arr,l,mid);
        mergesort(arr,mid+1, r);
        merge(arr, l, mid, r);
    }

    static void merge(int[] arr, int l, int mid, int r){
        int i = l;
        int j = mid+1;
        List<Integer> temp = new ArrayList<>();

        while((i <= mid) && (j <=r)) {
            if( arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                temp.add(arr[j]);
                j++;
            }
        }

        while(i <= mid){
            temp.add(arr[i]);
            i++;
        }

        while (j <=r) {
            temp.add(arr[j]);
            j++;
        }

        for (int k = l; k <= r; k++) {
            arr[k] = temp.get(k-l);
        }
    }
    public static void main(String[] args) {
        int[] arr = {4, 7, 5, 3, 1, 2, 6};
        mergesort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
