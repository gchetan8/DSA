
public class merge2SortedLL {

    static Node merge(Node first, Node second) {
        Node temp1 = first;
        Node temp2 = second;
        Node dummyHead = new Node(0);
        Node pointer = dummyHead;

        while(temp1!=null && temp2!=null) {
            if(temp1.val <= temp2.val) {
                pointer.next = temp1;
                temp1 = temp1.next;
            } else {
                pointer.next = temp2;
                temp2 = temp2.next;
            }
            pointer = pointer.next;
        }
        if(temp1!=null){
            pointer.next = temp1;
        }
        if(temp2!=null){
            pointer.next = temp2;
        }
        Node head = dummyHead.next;
        dummyHead.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 6, 8, 9};
        Node firstList = Node.convertArrToLL(arr1);
        Node.printLL(firstList);
        Node secondList = Node.convertArrToLL(arr2);
        Node.printLL(secondList);
        Node mergedList = merge(firstList, secondList);
        Node.printLL(mergedList);
    }
}
