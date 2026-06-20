public class DeleteDuplicatesSortedDLL {

    static DLLNode deleteDuplicates(DLLNode head) {
        DLLNode temp = head;
        while (temp != null && temp.next!=null) {
            DLLNode temp1 = temp.next;
            while(temp1!=null && temp1.val == temp.val){
                temp1 = temp1.next;
            }
            temp.next = temp1;
            if(temp1!=null)
                temp1.prev = temp;
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 3, 3, 3, 4, 4, 4, 9, 9, 9};
        DLLNode list = DLLNode.convertArrToDLL(arr);
        DLLNode updatedList = deleteDuplicates(list);
        DLLNode.printDLL(updatedList);
    }
}
