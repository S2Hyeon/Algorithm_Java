import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// [G5] 알약
public class BOJ_4811 {
    /*
    처음 꺼내는 알약은 반드시 온전한 알약이므로 이를 제외한 상태로 풀이한다.
    (첫 알약에 대한 반조각은 꺼내는 위치에 큰 의미가 없다.)

    첫날 먹은 온전한 알약을 제외하고 반조각을 꺼냈을 때 시점을 기준으로
    이전날까지 온전한 알약을 꺼낸 경우의 수 * 이후로 먹을 수 있는 온전한 알약을 꺼낸 경우의 수

    ex)
        N = 3일 때,
        1. W/HWW -> 바로 반조각을 꺼낸 경우
            dp[0] * dp[2] = 2
            남은 온전한 알약이 2개이므로 dp[2]와 같다.
            실제 경우의 수 : WHWWHH, WHWHWH

        2. W/WHW -> 온전한 알약을 꺼내고 그 다음 날에 반조각을 꺼낸 경우
            dp[1] * dp[1] = 1
            실제 경우의 수 : WWHHWH

        3. W/WWH -> 온전한 알약을 모두 꺼내고 그 다음 날에 남은 반조각을 모두 꺼내는 경우
            dp[2] * dp[0] = 2
            연속으로 2개의 온전한 알약을 꺼내는 경우의 수는 dp[2]와 같다.
            실제 경우의 수 : WWHWHH, WWWHHH


     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= 30; i++) {
            long sum = 0;

            for(int j = 0; j < i; j++) {
                sum += dp[j] * dp[i - 1 - j];
            }

            dp[i] = sum;
        }

        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }
}
