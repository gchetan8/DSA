public class Print1toN {
    static void printNumber(int i, int n) {
        if (i > n) return;
        System.out.println(i);
        printNumber(i+1, n);
    }

    public static void main(String[] args) {
        int n = 10;
        printNumber(1, n);
    }
}
