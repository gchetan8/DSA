public class deleteAllOccurences {

    static DLLNode deleteKey(DLLNode head, int k) {
        DLLNode temp = head;
        while(temp.next!=null) {
            if (temp.val == k) {
                if(temp == head) {
                    head = head.next;
                    temp = temp.next;
                }
                DLLNode prev = temp.prev;
                DLLNode nextNode = temp.next;
                if(nextNode!= null){
                    nextNode.prev  = prev;
                }
                if(prev != null)
                    prev.next = nextNode;
                temp = nextNode;
            } else
                temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 5, 2, 6, 7, 2, 8};
        DLLNode list = DLLNode.convertArrToDLL(arr);
        DLLNode updatedList = deleteKey(list, 2);
        DLLNode.printDLL(updatedList);
    }
}
