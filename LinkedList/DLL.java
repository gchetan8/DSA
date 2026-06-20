public class DLL {

    static DLLNode insertAtBeginning(DLLNode head, int key) {
        DLLNode temp = new DLLNode(key, head, null);
        head.prev = temp;
        return temp;
    }

    static DLLNode insertAfterLast(DLLNode head, int key) {
        DLLNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        DLLNode newNode = new DLLNode(key, null, temp);
        temp.next = newNode;
        return head;
    }

    static DLLNode insertAtKth(DLLNode head, int key, int k) {
        int i = 1;
        DLLNode temp = head;
        DLLNode prev = null;
        while (i != k) {
            prev = temp;
            temp = temp.next;
            i++;
        }
        DLLNode kNode = new DLLNode(key);
        kNode.next = temp;
        kNode.prev = prev;
        prev.next = kNode;
        temp.prev = kNode;
        return head;
    }

    static DLLNode deleteFirst(DLLNode head) {
        DLLNode temp = head.next;
        temp.prev = null;
        head.next = null;
        return temp;
    }

    static DLLNode deleteLast(DLLNode head) {
        DLLNode temp = head;
        DLLNode prev = null;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        temp.prev = null;
        prev.next = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        DLLNode head = DLLNode.convertArrToDLL(arr);
        DLLNode.printDLL(head);
        DLLNode newHead = insertAtBeginning(head, 0);
        DLLNode.printDLL(newHead);
        DLLNode updatedHead = insertAfterLast(newHead, 6);
        DLLNode.printDLL(updatedHead);
        DLLNode kNodeHead = insertAtKth(updatedHead, 7, 3);
        DLLNode.printDLL(kNodeHead);
        kNodeHead = deleteFirst(kNodeHead);
        DLLNode.printDLL(kNodeHead);
        kNodeHead = deleteLast(kNodeHead);
        DLLNode.printDLL(kNodeHead);
    }
}
