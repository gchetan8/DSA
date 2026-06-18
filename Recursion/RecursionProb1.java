import java.util.ArrayList;
import java.util.List;

public class RecursionProb1 {
    /*
Amazon sellers can bundle any combination of their products for a promotional offer.
Given a seller's product catalogue, generate all possible bundles.
A bundle can have any number of products — including an empty bundle (no promotion) or all products together.

i/p - products = [5, 2, 4]   // product IDs

Output -
[5, 2, 4]
[5, 2]
[5, 4]
[5]
[2, 4]
[2]
[4]
[]


Constraints:
All product IDs are unique
Empty bundle is valid
Order of elements in each bundle must match original catalogue order
*/


    static void printSubSeq(int ind, int[] arr, List<Integer> subSeq) {
        if(ind == arr.length) {
            System.out.println(subSeq);
            return;
        }


        // not pick the item at ind
        printSubSeq(ind+1, arr, subSeq);

        // pick the item at ind
        subSeq.add(arr[ind]);
        printSubSeq(ind+1, arr, subSeq);
        subSeq.removeLast();



    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4};
        printSubSeq(0, arr, new ArrayList<>());
    }
}
