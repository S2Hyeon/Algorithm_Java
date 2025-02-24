import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S3] 이친수
public class BOJ_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }

    private static long solution(int N) {
        if(N == 1 || N == 2) {
            return 1;
        }

        long[] dp = new long[N + 1];
        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[N];
    }
}
