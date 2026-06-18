public class SimplestRecursionWithBaseCondition {
    static int count = 1;

    static void printHello() {
        if (count > 5) return;
        System.out.println("Hello " + count);
        count++;
        printHello();
    }

    public static void main(String[] args) {
        printHello();
    }
}
