import java.util.ArrayList;
import java.util.List;

public class combinationSum {

/*
Input : candidates = [2, 3, 5, 4] , target = 7
Output : [ [2, 2, 3] , [3, 4] , [5, 2] ]
Explanation :
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
5 and 2 are candidates, and 5 + 2 = 7.
3 and 4 are candidates, and 3 + 4 = 7.
There are total three combinations.
*/

    static void printCombinationSum(int[] arr, int ind, List<List<Integer>> ans,
                                    int target, List<Integer> ds) {
        if(ind == arr.length) {
            if (target == 0) {
                System.out.println(ds);
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            printCombinationSum(arr, ind, ans,target - arr[ind], ds);
            ds.removeLast();
        }
        printCombinationSum(arr, ind+1, ans, target, ds);
    }

    public static void main(String[] args) {
        int[] arr = {2,3,5,4};
        int k = 7;
        List<List<Integer>> result = new ArrayList<>();
        printCombinationSum(arr, 0, result, k, new ArrayList<>());
        System.out.println(result);
    }

}