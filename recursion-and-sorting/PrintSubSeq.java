import java.util.ArrayList;
import java.util.List;

public class PrintSubSeq {

    static void printSubSeq(int[] arr, int i, List<Integer> subSeqArr, int k) {
        if(i == arr.length){
            if (k == 0) {
                System.out.println(subSeqArr);
            }
            return;
        }

        subSeqArr.add(arr[i]);
        printSubSeq(arr, i+1, subSeqArr, k - arr[i]);
        subSeqArr.removeLast();

        printSubSeq(arr, i+1, subSeqArr, k);
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4};
        int k = 7;
        printSubSeq(arr, 0, new ArrayList<>(), k);
    }
}
