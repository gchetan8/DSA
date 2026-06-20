public class upper_bound {
    static void upper_bound(int[] arr, int k) {
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n-1;

        while (low <= high) {
            int mid = (low + high)/2;
            if (arr[mid] > k) {
                ans = mid;
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
            System.out.println("Upper bound index is: " + ans);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 7, 8};
        upper_bound(arr, 4);
    }
}
