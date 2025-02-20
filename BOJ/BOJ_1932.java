import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [S1] 정수 삼각형
public class BOJ_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N][];
        int[][] dp = new int[N][];
        StringTokenizer st;
        int max = 0;

        for(int i = 1; i <= N; i++) {
            triangle[i - 1] = new int[i];
            dp[i - 1] = new int[i];
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int index = 0;
            while(st.hasMoreTokens()) {
                triangle[i][index++] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = triangle[0][0];

        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                if(dp[i + 1][j] < dp[i][j] + triangle[i + 1][j]) {
                    dp[i + 1][j] = dp[i][j] + triangle[i + 1][j];
                }

                if(dp[i + 1][j + 1] < dp[i][j] + triangle[i + 1][j + 1]) {
                    dp[i + 1][j + 1] = dp[i][j] + triangle[i + 1][j + 1];
                }
            }
        }

        for(int i = 0; i < dp[N - 1].length; i++) {
            max = Math.max(max, dp[N - 1][i]);
        }

        System.out.println(max);
    }
}
