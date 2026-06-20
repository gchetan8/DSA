public class searchInrotatedArray {

    static void search(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n-1;

        while(low <= high) {
            int mid = (low + high)/2;
            if(arr[mid] == k) {
                System.out.println("Found the element at index: " + mid);
                return;
            }
            // check if left is sorted
            if(arr[low] <= arr[mid]) {

                if((arr[low] <= k) && (k <= arr[mid])) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if((arr[mid] <= k) && (k <= arr[high])) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9 , 1, 2, 3, 4, 5, 6};
        search(arr, 1);
    }
}
