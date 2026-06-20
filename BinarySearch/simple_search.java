public class simple_search {

    static void binary_search(int[] arr, int key) {
        int n = arr.length - 1;
        int low = 0;
        int high = n;

        while(low <= high) {
            int mid = (low + high)/2;
            if(arr[mid] == key){
                System.out.println("found the key: " + arr[mid]);
                return;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("the key is not present in array");
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        binary_search(arr, 4);

    }
}
