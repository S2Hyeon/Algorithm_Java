import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [G5] 1로 만들기 2
public class BOJ_12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] history = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for(int i = 1; i < dp.length; i++) {
            int curCount = dp[i];

            if(i * 2 < dp.length && curCount < dp[i * 2]) {
                dp[i * 2] = curCount + 1;
                history[i * 2] = i;
            }

            if(i * 3 < dp.length && curCount < dp[i * 3]) {
                dp[i * 3] = curCount + 1;
                history[i * 3] = i;
            }

            if(i + 1 < dp.length && curCount < dp[i + 1]) {
                dp[i + 1] = curCount + 1;
                history[i + 1] = i;
            }
        }

        sb.append(dp[N]).append("\n");
        int index = N;

        while(index > 0) {
            sb.append(index).append(" ");
            index = history[index];
        }

        System.out.println(sb);
    }
}
