public class lower_bound {

    static void lower_bound(int[] arr, int k) {
        int n = arr.length;
        int ans = n;
        int low = 0;
        int high = n-1;

        while (low <= high) {
            int mid = (low + high)/2;
            if (arr[mid] >= k) {
                ans = mid;
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        if (ans < n)
            System.out.println("lower bound is: " + arr[ans]);
        else
            System.out.println("Element not in list");
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 7, 8};
        lower_bound(arr, 9);
    }
}
