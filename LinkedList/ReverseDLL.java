public class ReverseDLL {

    static DLLNode reverse(DLLNode head) {

        if(head.next == null) return head;
        DLLNode temp = head;
        DLLNode prev = null;
        while (temp!= null){
            prev = temp.prev;
            DLLNode next = temp.next;
            temp.next = prev;
            temp.prev = next;
            temp = temp.prev;
        }
        // note that we are not doing prev.next
        return prev.prev;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 9};
        DLLNode list = DLLNode.convertArrToDLL(arr);
        DLLNode reverseList = reverse(list);
        DLLNode.printDLL(reverseList);
    }
}
