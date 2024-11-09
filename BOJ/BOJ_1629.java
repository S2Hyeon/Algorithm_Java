import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S1] 곱셈
public class BOJ_1629 {
    /*
        a^8 = a^4 * a^4
            = (a^2 * a^2) * (a^2 * a^2)
            = ((a^1 * a^1) * (a^1 * a^1)) * ((a^1 * a^1) * (a^1 * a^1))

        a^9 = a^4 * a^4 * a
            = (a^2 * a^2) * (a^2 * a^2) * a
            = ((a^1 * a^1) * (a^1 * a^1)) * ((a^1 * a^1) * (a^1 * a^1)) * a
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B, C));

    }

    private static long pow(long A, long B, long C) {
        if(B == 1) {
            return A % C;
        } else {
            long temp = pow(A, B / 2, C);

            if(B % 2 == 1) {
                return (temp * temp % C) * A % C;
            }

            return temp * temp % C;
        }
    }
}
