package utilities;

public class UnaryParser {
    public static String parseToUnary(int n) {
        String u = "";
        for (int i = 0; i < n; i++) {
            u += "1";
        }
        return u;
    }

    public static int parseToDecimal(String u) {
        int n = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) != '1')
                throw new IllegalArgumentException();
            n++;
        }
        return n;
    }
}
