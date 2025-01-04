import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S3] 2xn 타일링
public class BOJ_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        /*
        dp[1] = 1, dp[2] = 2로 설정해두고 반복문에서 i = 3으로 시작하면
        n = 1일 때 dp[2] = 1 부분에서 범위 초과 에러가 나기 때문에
        dp[0] = 1로 임의설정을 해주고, 반복문에서 dp[2]의 값이 2가 되도록 해준다.
         */
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
        }

        System.out.println(dp[n]);
    }
}
