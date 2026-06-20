class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    static Node convertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node pointer = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            pointer.next = temp;
            pointer = temp;
        }
        return head;
    }

    static void printLL(Node head){
        while(head!=null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
