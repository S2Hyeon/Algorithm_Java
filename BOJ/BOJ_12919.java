import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [G5] A와 B 2
public class BOJ_12919 {
    static int result;
    static String strS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        strS = br.readLine();
        StringBuilder strT = new StringBuilder(br.readLine());
        recursive(strT);
        System.out.println(result);
    }

    private static void recursive(StringBuilder strT) {
        if(result == 1) {
            return;
        }

        if(strT.length() == strS.length()) {
            if(strT.toString().equals(strS)) {
                result = 1;
            }

            return;
        }

        if(strT.charAt(strT.length() - 1) == 'A') {
            recursive(new StringBuilder(strT).deleteCharAt(strT.length() - 1));
        }

        if(strT.charAt(0) == 'B') {
            recursive(new StringBuilder(strT).deleteCharAt(0).reverse());
        }
    }
}