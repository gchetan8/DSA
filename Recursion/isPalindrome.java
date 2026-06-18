public class isPalindrome {
    static boolean isPalindrome(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindrome(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        System.out.println("madam   : " + isPalindrome("madam",   0, 4)); // true
        System.out.println("racecar : " + isPalindrome("racecar", 0, 6)); // true
        System.out.println("hello   : " + isPalindrome("hello",   0, 4)); // false
        System.out.println("a       : " + isPalindrome("a",       0, 0)); // true
    }
}
