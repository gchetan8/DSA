// Floor and Ceil in a Sorted Array
// Floor: largest element <= x
// Ceil:  smallest element >= x
public class floorAndCeil {

    static int findFloor(int[] arr, int x) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= x) {
                ans = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    static int findCeil(int[] arr, int x) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println(findFloor(arr, 25)); // 20
        System.out.println(findCeil(arr, 25));  // 30
        System.out.println(findFloor(arr, 20)); // 20
        System.out.println(findCeil(arr, 20));  // 20
    }
}
