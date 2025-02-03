import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [G2] 로봇 조종하기
public class BOJ_2169 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];
        int[][] temp = new int[2][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            dp[0][i] = map[0][i];
        }

        // 첫줄은 오른쪽으만 갈 수 있으므로 오른쪽으로 이동하며 누적합을 구해준다.
        for(int i = 1; i < M; i++) {
            dp[0][i] += dp[0][i - 1];
        }

        // dp를 위해 왼쪽, 오른쪽 위쪽으로부터 현재 위치로 온다고 생각한다.
        // temp[0]은 왼쪽, temp[1]은 오른쪽으로 부터 합산되어 온 값
        for(int i = 1; i < N; i++) {
            // 왼쪽 -> 오른쪽부터 합산 시작
            temp[0][0] = dp[i - 1][0] + map[i][0]; // 제일 왼쪽은 위쪽에서 오는 경우밖에 없다.

            for(int j = 1; j < M; j++) { // 왼쪽에서 오는 값 vs 위쪽에서 오는 값
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + map[i][j];
            }

            // 오른쪽 -> 왼쪽 합산 시작
            temp[1][M - 1] = dp[i - 1][M - 1] + map[i][M - 1]; // 제일 오른쪽은 위쪽에서 오는 경우밖에 없다.

            for(int j = M - 2; j >= 0; j--) { // 오른쪽에서 오는 값 vs 위쪽에서 오는 값
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + map[i][j];
            }

            // 왼쪽에서 오는 값 vs 오른쪽에서 오는 값
            for(int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}
