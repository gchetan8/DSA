class DLLNode {
    int val;
    DLLNode next;
    DLLNode prev;
    DLLNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }


    DLLNode(int val, DLLNode next, DLLNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    static DLLNode convertArrToDLL(int[] arr) {
        DLLNode head = new DLLNode(arr[0], null, null);
        DLLNode pointer = head;
        for (int i = 1; i < arr.length; i++) {
            DLLNode temp = new DLLNode(arr[i], null, pointer);
            pointer.next = temp;
            pointer = temp;
        }
        return head;
    }

    static void printDLL(DLLNode head) {
        DLLNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}