public class recursion {

    static void func(int count, int k) {
        if( count == k) {
            return;
        }
        System.out.println("Hello world");
        func(count+1, k);
    }

    public static void main(String[] args) {
        func(0, 3);
    }
}
