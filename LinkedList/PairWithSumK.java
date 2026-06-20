import java.util.ArrayList;
import java.util.List;

public class PairWithSumK {

    static DLLNode findLast(DLLNode head) {
        DLLNode temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }

    static List<List<Integer>> pairWithSumK(DLLNode head, int k) {
        List<List<Integer>> result = new ArrayList<>();
        DLLNode lastNode = findLast(head);
        DLLNode first = head;
        // keeping the loop as while(first !=null && lastNode!=null)
        // We will have result with same pairs in 2 orders - [2, 6] & [6, 2]
        // Since we don't want to count them as 2, we should update the while loop condition accordingly.
        while(first.val < lastNode.val ) {
            int tSum = first.val + lastNode.val;
            if (tSum == k) {
                result.add(List.of(first.val, lastNode.val));
                first = first.next;
                lastNode = lastNode.prev;
            }
            else if (tSum < k) {
                first = first.next;
            } else {
                lastNode = lastNode.prev;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 9};
        int k = 5;
        DLLNode list = DLLNode.convertArrToDLL(arr);
        List<List<Integer>> result =  pairWithSumK(list, k);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
