import java.util.ArrayList;
import java.util.List;

public class recurProb2 {
    /*
A payments fraud team is investigating suspicious activity.
They have a list of transaction amounts made by a user in a single day.
Find all combinations of transactions that add up to exactly ₹K — each transaction can only be used once.
This helps identify if a large amount was deliberately split across multiple smaller transactions to avoid detection.

i/p: transactions = [1000, 2000, 3000, 4000, 5000]
K = 5000

o/p:
[1000, 4000]
[2000, 3000]
[5000]

Constraints:
Each transaction can be used only once
Print all valid combinations, not just one
Order within each combination must match original order
     */

    static int printSubSeq(int ind, int[] arr, int subSeqSum, List<Integer> subSeq, int k) {
        if(ind == arr.length) {
            if (subSeqSum == k) {
                return 1;
            }
            return 0;
        }


        // not pick the item at ind
        int notPick = printSubSeq(ind+1, arr, subSeqSum, subSeq, k);

        // pick the item at ind
        subSeq.add(arr[ind]);
        int Pick = printSubSeq(ind+1, arr, subSeqSum+arr[ind], subSeq,k);
        subSeq.removeLast();

        return notPick + Pick;

    }

    public static void main(String[] args) {
        int[] arr = {1000, 2000, 3000, 4000, 5000};
        System.out.println(printSubSeq(0, arr, 0, new ArrayList<>(), 5000));
    }

}
