public class PrintNTimes {

    public static void printRCB(int i,int n) {
        if (i > n) return;
        System.out.println("Ee sala nu cup namdu!!");
        printRCB(i+1,n);
    }

    public static void main(String[] args) {
        printRCB(1,10);
    }
}
