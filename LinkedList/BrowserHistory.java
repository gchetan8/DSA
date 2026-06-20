class BrowserNode {
    String url;
    BrowserNode next;
    BrowserNode prev;

    BrowserNode(String url) {
        this.url = url;
    }
}

public class BrowserHistory {
    BrowserNode current;

    BrowserHistory(String homepage) {
        current = new BrowserNode(homepage);
    }

    void visit(String url) {
        BrowserNode newNode = new BrowserNode(url);
        newNode.prev = current;
        current.next = newNode;  // cuts off forward history
        current = newNode;
    }

    String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("leetcode.com");
        bh.visit("google.com");
        bh.visit("facebook.com");
        bh.visit("youtube.com");
        System.out.println(bh.back(1));     // facebook.com
        System.out.println(bh.back(1));     // google.com
        System.out.println(bh.forward(1));  // facebook.com
        bh.visit("linkedin.com");           // forward history cleared
        System.out.println(bh.forward(2)); // linkedin.com (no forward to go)
        System.out.println(bh.back(2));    // google.com
        System.out.println(bh.back(7));    // leetcode.com (stops at start)
    }
}
