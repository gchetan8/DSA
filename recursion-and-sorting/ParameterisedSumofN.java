public class ParameterisedSumofN {

    static int SumOfN(int n, int sum) {
        if(n == 0) return sum;
        return SumOfN(n-1, sum + n);
    }

    public static void main(String[] args) {
        int result = SumOfN(10, 0);
        System.out.println(result);
    }
}
