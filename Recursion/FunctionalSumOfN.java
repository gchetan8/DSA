public class FunctionalSumOfN {

    static int SumOfN(int n) {
        if (n == 0) return 0;
        return n + SumOfN(n-1);
    }

    public static void main(String[] args) {
       int result = SumOfN(10);
       System.out.println(result);
    }
}
