import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [G5] 1, 2, 3 더하기 4
/*
    n이 1, 2로만 구성되었다면 중복되는 경우가 없다.
    하지만, 1, 2, 3으로 구성된 경우, 2와 3을 사용하는데 있어 중복되는 경우가 생긴다.
        ex) 5의 경우
            1로만 구성된 경우
            1 + 1 + 1 + 1 + 1

            (n - 2)의 모든 결과에 2를 추가한 경우
            1 + 1 + 1 + 2
            1 + 1 + 2
            3 + 2

            (n - 3)의 모든 결과에 3을 추가한 경우
            1 + 1 + 3
            2 + 3

            위와 같이 2 + 3이 중복되는 경우가 생긴다.

    따라서 다음 순서와 같이 경우의 수를 구한다.
        1. 1로만 구성된 경우의 수(1)을 먼저 만든다.
        2. 3이 없는(1, 2로만 구성된) 경우의 수를 먼저 만든다.
        3. dp[3]부터 3을 추가한 뒤, n - 3의 경우의 수로부터 3을 추가하는 경우의 수를 더해준다.

    참고 : https://ku-hug.tistory.com/209, https://apple-apeach.tistory.com/18
 */
public class BOJ_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[10001];
        Arrays.fill(dp, 1); // 1의 합으로만 구성된 경우는 미리 더해준다.

        // n이 1, 2로만 구성된 경우
        for(int i = 2; i < dp.length; i++) {
            dp[i] += dp[i - 2];
        }

        // n이 1, 2, 3으로 구성된 경우
        for(int i = 3; i < dp.length; i++) {
            dp[i] += dp[i - 3];
        }

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}
