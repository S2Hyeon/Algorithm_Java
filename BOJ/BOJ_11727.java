import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2×n 타일링 2
public class BOJ_11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));
    }

    private static int solution(int N) {
        if(N == 1) {
            return 1;
        } else if(N == 2) {
            return 3;
        }

        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i < dp.length; i++) {
            dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10_007;
        }

        return dp[N];
    }
}