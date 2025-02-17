import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S3] 1, 2, 3 더하기
public class BOJ_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[11];
        StringBuilder sb = new StringBuilder();

        /*
            1 + 2, 2 + 1은 다른 경우임
            A[4] =
            1 + A[3],
            2 + A[2],
            3 + A[1]
            가 되므로 점화식은 아래와 같음
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1]
         */

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}
