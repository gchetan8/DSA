public class Print1toNTailRecursion {

    static void printNumber(int i, int n) {
        if (i < 1) return;
        printNumber(i-1, n);
        System.out.println(i);
    }

    public static void main(String[] args) {
        printNumber(10, 10);
    }
}
