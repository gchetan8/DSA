public class SimplestRecursion {
    static int count = 0;

    static void printHello() {
        count++;
        System.out.println("Hello " + count);
        printHello();
    }

    public static void main(String[] args) {
        printHello();
    }
}
