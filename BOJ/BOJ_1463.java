import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [S3] 1로 만들기
public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        for(int i = 2; i <= N; i++) {
            int min = dp[i - 1]; // 1을 빼는 경우
            if(i % 2 == 0) { // 2로 나누는 경우
                min = Math.min(min, dp[i / 2]);
            }

            if (i % 3 == 0) { // 3으로 나누는 경우
                min = Math.min(min, dp[i / 3]);
            }

            dp[i] = min + 1; // 현재 연산 추가
        }

        System.out.println(dp[N]);
    }
}
